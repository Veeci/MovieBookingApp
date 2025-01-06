package com.example.baseproject.domain.utils

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Keep
@Parcelize
data class BaseResponse<T>(
    @field:SerializedName("data")
    val data: @RawValue T? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("errorCode")
    val errorCode: Int? = null,
) : Parcelable {
    fun isSuccess() = status.equals("success", true)
}
