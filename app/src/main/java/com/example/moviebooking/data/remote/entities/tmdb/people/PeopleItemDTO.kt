package com.example.moviebooking.data.remote.entities.tmdb.people

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleItemDTO(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("results") val results: List<PeopleItem?>? = null,
    @SerializedName("total_results") val totalResults: Int? = null
) : Parcelable

@Parcelize
data class PeopleItem(
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("known_for") val knownFor: List<KnownForItem?>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null
) : Parcelable

@Parcelize
data class KnownForItem(
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int?>? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("media_type") val mediaType: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("vote_average") val voteAverage: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("origin_country") val originCountry: List<String?>? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("name") val name: String? = null
) : Parcelable
