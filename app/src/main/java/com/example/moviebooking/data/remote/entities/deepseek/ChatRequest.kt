package com.example.moviebooking.data.remote.entities.deepseek

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatRequest (
    @field:SerializedName("model")
    val model: String = "deepseek/deepseek-chat-v3-0324:free",
    @field:SerializedName("messages")
    val messages: List<ChatMessage>
) : Parcelable