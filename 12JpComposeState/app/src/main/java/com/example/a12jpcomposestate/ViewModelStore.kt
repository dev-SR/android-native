package com.example.a12jpcomposestate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

data class User(val name: String)
class ViewModelStore : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Empty())
    val uiState: StateFlow<UiState<List<User>>> = _uiState


    init {
        fetchWeather()
    }

    //List<out T> in Kotlin is equivalent to List<? extends T> in Java.
    sealed class UiState<out T> {
        class Empty<out T> : UiState<T>()
        class Loading<out T> : UiState<T>()
        data class Success<out T>(val data: T) : UiState<T>()
        data class Error<out T>(val message: String) : UiState<T>()
    }

    private fun fetchWeather() {
        _uiState.value = UiState.Loading()
        viewModelScope.launch(Dispatchers.IO) {

            getListOfUsers().collect {
//                _uiState.value = UiState.Success(it)
                _uiState.value =UiState.Error("Error")
            }
        }
    }

    suspend fun getListOfUsers() = flow {
        val listUsers = listOf(User("A"), User("B"), User("C"), User("D"))
        delay(2000)
        emit(listUsers)
    }
    suspend fun throwError() = flow {
        delay(2000)
        emit(0)
    }
}