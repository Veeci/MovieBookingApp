package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.remote.entities.deepseek.ChatMessage
import com.example.moviebooking.data.remote.entities.deepseek.ChatRequest
import com.example.moviebooking.data.remote.entities.deepseek.ChatResponse
import com.example.moviebooking.domain.usecases.chat.ChatbotUseCase

class ChatbotViewModel(
    private val chatbotUseCase: ChatbotUseCase
) : BaseViewModel() {
    private val _chatMessages: MutableLiveData<ResponseStatus<ChatResponse>> = MutableLiveData()
    val chatMessages: LiveData<ResponseStatus<ChatResponse>> = _chatMessages

    fun sendMessage(message: ChatRequest) {
        launchCoroutine {
            chatbotUseCase.sendMessage(message).collect { response ->
                _chatMessages.postValue(response)
            }
        }
    }
}