package com.monty.jetgooglerepository.android.presentation.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SplashState())

    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SplashState())

    init {
        viewModelScope.launch {
            delay(3000)
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}