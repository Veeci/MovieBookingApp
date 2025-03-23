package com.example.moviebooking.data.remote.entities.tmdb.people

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleSearchingResultDTO(
	@SerializedName("page") val page: Int? = null,
	@SerializedName("total_pages") val totalPages: Int? = null,
	@SerializedName("results") val results: List<PeopleResultsItem>? = emptyList(),
	@SerializedName("total_results") val totalResults: Int? = null
) : Parcelable

@Parcelize
data class PeopleResultsItem(
	@SerializedName("gender") val gender: Int? = null,
	@SerializedName("known_for_department") val knownForDepartment: String? = null,
	@SerializedName("original_name") val originalName: String? = null,
	@SerializedName("popularity") val popularity: Double? = 0.0,
	@SerializedName("known_for") val knownFor: List<KnownFor>? = emptyList(),
	@SerializedName("name") val name: String? = null,
	@SerializedName("profile_path") val profilePath: String? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("adult") val adult: Boolean? = null
) : Parcelable

@Parcelize
data class KnownFor(
	@SerializedName("overview") val overview: String? = null,
	@SerializedName("original_language") val originalLanguage: String? = null,
	@SerializedName("original_title") val originalTitle: String? = null,
	@SerializedName("video") val video: Boolean? = null,
	@SerializedName("title") val title: String? = null,
	@SerializedName("genre_ids") val genreIds: List<Int>? = emptyList(),
	@SerializedName("poster_path") val posterPath: String? = null,
	@SerializedName("backdrop_path") val backdropPath: String? = null,
	@SerializedName("media_type") val mediaType: String? = null,
	@SerializedName("release_date") val releaseDate: String? = null,
	@SerializedName("popularity") val popularity: Double? = 0.0,
	@SerializedName("vote_average") val voteAverage: Double? = 0.0,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("adult") val adult: Boolean? = null,
	@SerializedName("vote_count") val voteCount: Int? = 0,
	@SerializedName("first_air_date") val firstAirDate: String? = null,
	@SerializedName("origin_country") val originCountry: List<String>? = emptyList(),
	@SerializedName("original_name") val originalName: String? = null,
	@SerializedName("name") val name: String? = null
) : Parcelable
