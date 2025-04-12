package com.example.moviebooking.domain.usecases.chat

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.deepseek.ChatRequest
import com.example.moviebooking.data.remote.entities.deepseek.ChatResponse
import kotlinx.coroutines.flow.Flow

interface ChatbotUseCase {
    suspend fun sendMessage(message: ChatRequest): Flow<ResponseStatus<ChatResponse>>
}