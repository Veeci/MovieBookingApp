package com.example.baseproject.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
    DTO represents the raw API response.
 */
@Keep
@Parcelize
open class BaseDTO(
    @field:SerializedName("id")
    open val id: String? = null,
) : Parcelable