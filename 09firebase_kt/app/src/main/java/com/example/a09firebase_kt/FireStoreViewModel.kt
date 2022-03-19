package com.example.a09firebase_kt

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class FireStoreViewModel(val context: Context, var repository: FirebaseRepository) :
    ViewModel() {
    private val todos = MutableLiveData<List<Todo>>()
    private var firebaseRepo: FirebaseRepository? = null;


    init {
        firebaseRepo = repository

    }

    fun getTodos(): LiveData<List<Todo>> {
        viewModelScope.launch {
            firebaseRepo?.let {
                firebaseRepo?.read {
                    todos.postValue(it)
                }
            }
        }
        return todos;
    }

    fun addTodo() {
        viewModelScope.launch {
            firebaseRepo?.let {
                firebaseRepo?.add(Todo("Todo No: ${Random.nextInt(5, 100)}")) {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    class Factory(private val context: Context, private val initial: FirebaseRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FireStoreViewModel(context, initial) as T
        }
    }
}