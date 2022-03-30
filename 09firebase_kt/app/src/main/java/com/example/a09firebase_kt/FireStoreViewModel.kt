package com.example.a09firebase_kt

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlin.random.Random


class FireStoreViewModel(var repository: FirebaseRepository) :
    ViewModel() {
//    fun getTodosAsLiveData() = repository.getTodosFromFirestore().asLiveData()
    fun getTodosAsLiveData2() = liveData {
        repository.getTodosFromFirestore().collect {
            emit(it)
        }
    }
//
//    fun getTodosAsFlow() = repository.getTodosFromFirestore()

    private val _addMessage = MutableLiveData<UiState<Todo>>()
    val addMessage: LiveData<UiState<Todo>> = _addMessage


    fun addTodo() {
        viewModelScope.launch {
            repository.addTodoToFirestore(Todo("Todo No: ${Random.nextInt(5, 100)}")).collect() {
                _addMessage.value = it as UiState<Todo>
            }
        }
    }


    class Factory(private val initial: FirebaseRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FireStoreViewModel(initial) as T
        }
    }
}