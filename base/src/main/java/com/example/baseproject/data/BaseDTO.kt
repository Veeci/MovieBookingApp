package com.example.baseproject.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
open class BaseDTO(
    @field:SerializedName("id")
    open val id: String? = null,
) : Parcelable

fun BaseDTO.mapToModel(): BaseModel? {
    id?.let {
        return BaseModel(
            id = this.id,
        )
    } ?: return null
}

fun List<BaseDTO>.mapToModels() = map { it.mapToModel() }
