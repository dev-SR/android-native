package com.example.a08mvvp_kt


import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage


    private var isSave = true
    private lateinit var subscriberToUpdateOrDelete: Subscriber

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun initUpdateAndDeleteButton(subscriber: Subscriber) {
        isSave = false
        //init Subscriber Reference : [subscriber id is important to save for later usage]
        subscriberToUpdateOrDelete = subscriber
        // update view
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


    fun saveOrUpdate(name: String, email: String) {
        if (isSave) {
            insert(Subscriber(name, email))
            inputName.value = ""
            inputEmail.value = ""
        } else {
//            Log.d("Test", subscriberToSaveOrUpdate.toString())
            //update reference with current input content
            subscriberToUpdateOrDelete.name = name
            subscriberToUpdateOrDelete.email = email
            update(subscriberToUpdateOrDelete)
            inputName.value = ""
            inputEmail.value = ""
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            isSave = true
        }
    }

    fun deleteOrClearAll() {
        if (isSave) {
            clearAll()
        } else {
            subscriberToUpdateOrDelete?.let {
                delete(subscriberToUpdateOrDelete)
                inputName.value = ""
                inputEmail.value = ""
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                isSave = true
            }
        }
    }


    private fun update(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.update(subscriber)
            statusMessage.value = Event("Subscriber Updated Successfully")


        }
    }

    private fun insert(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.insert(subscriber)
            statusMessage.value = Event("Subscriber Inserted Successfully")
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
            statusMessage.value = Event("All Subscribers Deleted Successfully")

        }
    }

    private fun delete(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.delete(subscriber)
            statusMessage.value = Event("Subscriber Deleted Successfully")

        }
    }


    //  Flow -> then convert to liveData
    fun getSavedSubscribers() = repository.getAllSubscriber().asLiveData()


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
