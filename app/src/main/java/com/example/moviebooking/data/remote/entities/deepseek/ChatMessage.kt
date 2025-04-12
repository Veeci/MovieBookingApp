package com.example.moviebooking.data.remote.entities.deepseek

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatMessage(
    @field:SerializedName("role")
    val role: String = "user",
    @field:SerializedName("content")
    val content: String,
    var isSent: Boolean = false,
    var isTyping: Boolean = false
) : Parcelable