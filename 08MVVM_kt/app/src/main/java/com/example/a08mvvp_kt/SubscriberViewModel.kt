package com.example.a08mvvp_kt


import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private var isSave = true
    private lateinit var subscriberToSaveOrUpdate: Subscriber

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }


    fun saveOrUpdate(name: String, email: String) {
        if (isSave) {
            insert(Subscriber(name, email))
            inputName.value = ""
            inputEmail.value = ""
        } else {
            update(subscriberToSaveOrUpdate)
        }
    }

    fun deleteOrClearAll(subscriber: Subscriber? = null) {
        if (isSave) {
            clearAll()
        } else {
            subscriber?.let {
                delete(it)
            }
        }
    }


    private fun update(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.update(subscriber)
        }
    }

    private fun insert(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.insert(subscriber)
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    private fun delete(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.delete(subscriber)
        }
    }

//Using liveData
//    fun getSavedSubscribers(): LiveData<List<Subscriber>> = repository.getAllSubscriber()
//  Flow -> then convert to liveData
    fun getSavedSubscribers() = liveData {
        repository.getAllSubscriber().collect {
            emit(it)
        }
    }

    class Factory(
        private val repository: SubscriberRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
                return SubscriberViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown View Model class")
        }
    }
}
