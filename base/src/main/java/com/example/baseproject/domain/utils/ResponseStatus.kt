package com.example.baseproject.domain.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

sealed class ResponseStatus<out T> {
    data class Success<out T>(val data: T) : ResponseStatus<T>()

    data class Error(val message: String = "", val errorCode: Int = 500) : ResponseStatus<Nothing>()

    data object Loading : ResponseStatus<Nothing>()
}

fun <T> ResponseStatus<T>.observer(response: MutableLiveData<ResponseStatus<T>>) {
    when (this) {
        is ResponseStatus.Loading -> {
            response.postValue(ResponseStatus.Loading)
        }

        is ResponseStatus.Success -> {
            response.postValue(ResponseStatus.Success(this.data))
        }

        is ResponseStatus.Error -> {
            response.postValue(ResponseStatus.Error(message = this.message))
        }
    }
}

fun <T> ResponseStatus<T>.observer(response: MutableStateFlow<ResponseStatus<T>>) {
    when (this) {
        is ResponseStatus.Loading -> {
            response.value = ResponseStatus.Loading
        }

        is ResponseStatus.Success -> {
            response.value = ResponseStatus.Success(this.data)
        }

        is ResponseStatus.Error -> {
            response.value = ResponseStatus.Error(message = this.message)
        }
    }
}
