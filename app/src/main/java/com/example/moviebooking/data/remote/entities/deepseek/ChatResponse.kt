package com.example.moviebooking.data.remote.entities.deepseek

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ChatResponse(
	@field:SerializedName("provider")
	val provider: String? = null,
	@field:SerializedName("created")
	val created: Int? = null,
	@field:SerializedName("usage")
	val usage: Usage? = null,
	@field:SerializedName("model")
	val model: String? = null,
	@field:SerializedName("id")
	val id: String? = null,
	@field:SerializedName("object")
	val objectType: String? = null,
	@field:SerializedName("choices")
	val choices: List<ChoicesItem?>? = null
) : Parcelable

@Parcelize
data class Message(
	@field:SerializedName("role")
	val role: String? = null,
	@field:SerializedName("reasoning")
	val reasoning: String? = null,
	@field:SerializedName("refusal")
	val refusal: String? = null,
	@field:SerializedName("content")
	val content: String? = null
) : Parcelable

@Parcelize
data class Usage(
	@field:SerializedName("completion_tokens")
	val completionTokens: Int? = null,
	@field:SerializedName("prompt_tokens")
	val promptTokens: Int? = null,
	@field:SerializedName("total_tokens")
	val totalTokens: Int? = null
) : Parcelable

@Parcelize
data class ChoicesItem(
	@field:SerializedName("finish_reason")
	val finishReason: String? = null,
	@field:SerializedName("native_finish_reason")
	val nativeFinishReason: String? = null,
	@field:SerializedName("index")
	val index: Int? = null,
	@field:SerializedName("message")
	val message: Message? = null,
	@field:SerializedName("logprobs")
	val logprobs: String? = null
) : Parcelable
