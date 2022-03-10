package com.example.a08mvvp_kt

import androidx.lifecycle.ViewModel

class Model {
    var count = 0

    fun getUpdatedCounter(): Int {
        return ++count
    }
}

class MainActivityViewModel : ViewModel() {
    private var model: Model = Model()
    fun getCurrentCount(): Int {
        return model.count
    }

    fun getUpdatedCounter(): Int {
        return model.getUpdatedCounter()
    }
}