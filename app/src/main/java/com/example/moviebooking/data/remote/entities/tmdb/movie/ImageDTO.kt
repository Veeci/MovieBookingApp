package com.example.moviebooking.data.remote.entities.tmdb.movie

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Image(
	@field:SerializedName("backdrops")
	val backdrops: List<BackdropsItem?>? = null,
	@field:SerializedName("posters")
	val posters: List<PostersItem?>? = null,
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("logos")
	val logos: List<LogosItem?>? = null
) : Parcelable

@Parcelize
data class LogosItem(
	@SerializedName("aspect_ratio")
	val aspectRatio: Double? = null,
	@SerializedName("file_path")
	val filePath: String? = null,
	@SerializedName("vote_average")
	val voteAverage: Double? = null,
	@SerializedName("width")
	val width: Int? = null,
	@SerializedName("iso_639_1")
	val iso6391: String? = null,
	@SerializedName("vote_count")
	val voteCount: Int? = null,
	@SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class PostersItem(
	@SerializedName("aspect_ratio")
	val aspectRatio: Double? = null,
	@SerializedName("file_path")
	val filePath: String? = null,
	@SerializedName("vote_average")
	val voteAverage: Double? = null,
	@SerializedName("width")
	val width: Int? = null,
	@SerializedName("iso_639_1")
	val iso6391: String? = null,
	@SerializedName("vote_count")
	val voteCount: Int? = null,
	@SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class BackdropsItem(
	@SerializedName("aspect_ratio")
	val aspectRatio: Double? = null,
	@SerializedName("file_path")
	val filePath: String? = null,
	@SerializedName("vote_average")
	val voteAverage: Double? = null,
	@SerializedName("width")
	val width: Int? = null,
	@SerializedName("iso_639_1")
	val iso6391: String? = null,
	@SerializedName("vote_count")
	val voteCount: Int? = null,
	@SerializedName("height")
	val height: Int? = null
) : Parcelable