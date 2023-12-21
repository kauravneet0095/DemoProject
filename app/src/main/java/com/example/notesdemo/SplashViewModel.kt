package com.example.notesdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private var isReadyToMove = false

    init {
        viewModelScope.launch {
            delay(2000)
            isReadyToMove = true
        }
    }

    fun isReady(): Boolean {
        return isReadyToMove
    }
}