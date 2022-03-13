package com.example.a08mvvp_kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivityViewModel(initial: Int) : ViewModel() {
    //    var count:Int = 0
    private val count = MutableLiveData<Int>()

    //    init {count = initial }
    init {
        count.value = initial
    }

    fun getCountValue(): LiveData<Int> = count
//    fun getUpdatedCounter(): Int {
//        return ++count
//    }

    fun updateCounter() {
        count.value = (count.value!! + 1)
    }

    class Factory(private val initial: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(initial) as T
        }
    }



}