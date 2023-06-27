package com.nsicyber.travelandshare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel : ViewModel() {

    fun startSplash(completion: () -> Unit) {
        viewModelScope.launch {
            delay(2000)
            completion()
        }
    }

}

