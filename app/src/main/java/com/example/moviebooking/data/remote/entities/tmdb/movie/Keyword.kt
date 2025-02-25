package com.example.moviebooking.data.remote.entities.tmdb.movie

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Keyword(
	@SerializedName("keywords")
	val keywords: List<KeywordsItem?>? = null,
	@SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class KeywordsItem(
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("id")
	val id: Int? = null
) : Parcelable
