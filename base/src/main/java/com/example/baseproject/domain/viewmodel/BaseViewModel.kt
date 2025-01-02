package com.example.baseproject.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

sealed class ErrorState {
    data class GenericError(val message: String) : ErrorState()

    data class NetworkError(val exception: Exception) : ErrorState()
}

open class BaseViewModel : ViewModel() {
    private val _errorState = MutableLiveData<ErrorState>()
    val errorState: LiveData<ErrorState> = _errorState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val exceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            val errorState =
                when (throwable) {
                    is IOException -> ErrorState.NetworkError(throwable)
                    else -> ErrorState.GenericError(throwable.message ?: "Unknown error")
                }
            _errorState.postValue(errorState)
        }

    fun launchCoroutine(block: suspend CoroutineScope.() -> Unit) {
        _isLoading.value = true
        viewModelScope.launch(context = Dispatchers.IO + exceptionHandler) {
            try {
                block()
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
