package com.example.moviebooking.data.remote.entities.tmdb.movie

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Credit(
	@SerializedName("cast")
	val cast: List<CastItem?>? = null,
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("crew")
	val crew: List<CrewItem?>? = null
) : Parcelable

@Parcelize
data class CrewItem(
	@SerializedName("adult")
	val adult: Boolean? = null,
	@SerializedName("gender")
	val gender: Int? = null,
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("known_for_department")
	val knownForDepartment: String? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("original_name")
	val originalName: String? = null,
	@SerializedName("popularity")
	val popularity: Double? = null,
	@SerializedName("profile_path")
	val profilePath: String? = null,
	@SerializedName("credit_id")
	val creditId: String? = null,
	@SerializedName("department")
	val department: String? = null,
	@SerializedName("job")
	val job: String? = null
) : Parcelable

@Parcelize
data class CastItem(
	@SerializedName("adult")
	val adult: Boolean? = null,
	@SerializedName("cast_id")
	val castId: Int? = null,
	@SerializedName("character")
	val character: String? = null,
	@SerializedName("credit_id")
	val creditId: String? = null,
	@SerializedName("gender")
	val gender: Int? = null,
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("known_for_department")
	val knownForDepartment: String? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("original_name")
	val originalName: String? = null,
	@SerializedName("order")
	val order: Int? = null,
	@SerializedName("popularity")
	val popularity: Double? = null,
	@SerializedName("profile_path")
	val profilePath: String? = null
) : Parcelable