package com.example.moviebooking.data.remote.entities.tmdb

import android.os.Parcelable
import com.example.baseproject.data.BaseDTO
import com.example.moviebooking.data.local.entities.MovieGenreEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    @field:SerializedName("genres")
    val genres: List<Genre?>? = null
): Parcelable, BaseDTO() {
    fun toGenreEntities(): List<MovieGenreEntity> {
        return genres?.mapNotNull { it?.toEntity() } ?: emptyList()
    }
}

@Parcelize
data class Genre (
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("name")
    val name: String? = null
): Parcelable {
    fun toEntity(): MovieGenreEntity {
        return MovieGenreEntity(
            name = this.name
        )
    }
}