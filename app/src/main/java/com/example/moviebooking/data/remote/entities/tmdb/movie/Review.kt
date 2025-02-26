package com.example.moviebooking.data.remote.entities.tmdb.movie

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Review(
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("page")
	val page: Int? = null,
	@SerializedName("total_pages")
	val totalPages: Int? = null,
	@SerializedName("results")
	val results: List<ReviewItem?>? = null,
	@SerializedName("total_results")
	val totalResults: Int? = null
) : Parcelable

@Parcelize
data class ReviewItem(
	@SerializedName("author_details")
	val authorDetails: AuthorDetails? = null,
	@SerializedName("updated_at")
	val updatedAt: String? = null,
	@SerializedName("author")
	val author: String? = null,
	@SerializedName("created_at")
	val createdAt: String? = null,
	@SerializedName("id")
	val id: String? = null,
	@SerializedName("content")
	val content: String? = null,
	@SerializedName("url")
	val url: String? = null
) : Parcelable

@Parcelize
data class AuthorDetails(
	@SerializedName("avatar_path")
	val avatarPath: String? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("rating")
	val rating: Int? = null,
	@SerializedName("username")
	val username: String? = null
) : Parcelable