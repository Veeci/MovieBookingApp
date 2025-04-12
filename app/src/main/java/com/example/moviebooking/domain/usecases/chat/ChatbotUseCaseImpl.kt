package com.example.moviebooking.domain.usecases.chat

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.deepseek.ChatRequest
import com.example.moviebooking.data.remote.entities.deepseek.ChatResponse
import com.example.moviebooking.data.remote.services.deepseek.DeepSeekService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class ChatbotUseCaseImpl(
    private val apiService: DeepSeekService
) : ChatbotUseCase, ApiHandler {
    override suspend fun sendMessage(message: ChatRequest): Flow<ResponseStatus<ChatResponse>> {
        return flow {
            emit(handleApi { apiService.sendMessage(message) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                message = e.message ?: "Unknown Error",
                errorCode = 500
            ))
        }.flowOn(Dispatchers.IO)
    }
}