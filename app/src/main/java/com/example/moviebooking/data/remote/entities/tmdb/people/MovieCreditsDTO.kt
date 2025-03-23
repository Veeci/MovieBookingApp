package com.example.moviebooking.data.remote.entities.tmdb.people

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCreditsDTO(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cast") val cast: List<MovieCastItem>? = null,
    @SerializedName("crew") val crew: List<MovieCrewItem>? = null
) : Parcelable

@Parcelize
data class MovieCastItem(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("character") val character: String? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("order") val order: Int? = null
) : Parcelable

@Parcelize
data class MovieCrewItem(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("department") val department: String? = null,
    @SerializedName("job") val job: String? = null
) : Parcelable
