package com.example.a16jpcscaffoldtemplate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Used to communicate between screens.
 */
class MainViewModel : ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(500L)
            _loading.emit(false)
        }
    }
    private val _currentScreen = MutableStateFlow<Screens>(Screens.HomeScreens.Home)
    val currentScreen: StateFlow<Screens> = _currentScreen.asStateFlow()

    fun setCurrentScreen(screen: Screens) {
        viewModelScope.launch {
            _currentScreen.emit(screen)
        }
    }

    private val _clickCount = MutableStateFlow(0)
    val clickCount: StateFlow<Int> = _clickCount

    fun updateClick(value: Int) {
        viewModelScope.launch {
            _clickCount.emit(value)
        }
    }
}