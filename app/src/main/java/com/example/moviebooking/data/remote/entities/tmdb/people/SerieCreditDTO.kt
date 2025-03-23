package com.example.moviebooking.data.remote.entities.tmdb.people

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesCreditDTO(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cast") val cast: List<SerieCastItem>? = emptyList(),
    @SerializedName("crew") val crew: List<SerieCrewItem>? = emptyList()
) : Parcelable

@Parcelize
data class SerieCastItem(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = emptyList(),
    @SerializedName("id") val id: Int? = null,
    @SerializedName("origin_country") val originCountry: List<String>? = emptyList(),
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = 0.0,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = 0.0,
    @SerializedName("vote_count") val voteCount: Int? = 0,
    @SerializedName("character") val character: String? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("episode_count") val episodeCount: Int? = 0
) : Parcelable

@Parcelize
data class SerieCrewItem(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = emptyList(),
    @SerializedName("id") val id: Int? = null,
    @SerializedName("origin_country") val originCountry: List<String>? = emptyList(),
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = 0.0,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = 0.0,
    @SerializedName("vote_count") val voteCount: Int? = 0,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("department") val department: String? = null,
    @SerializedName("episode_count") val episodeCount: Int? = 0,
    @SerializedName("job") val job: String? = null
) : Parcelable
