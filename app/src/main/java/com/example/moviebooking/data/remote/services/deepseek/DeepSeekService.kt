package com.example.moviebooking.data.remote.services.deepseek

import com.example.moviebooking.data.remote.entities.deepseek.ChatRequest
import com.example.moviebooking.data.remote.entities.deepseek.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepSeekService {
    @POST("chat/completions")
    suspend fun sendMessage(
        @Body request: ChatRequest
    ) : Response<ChatResponse>
}