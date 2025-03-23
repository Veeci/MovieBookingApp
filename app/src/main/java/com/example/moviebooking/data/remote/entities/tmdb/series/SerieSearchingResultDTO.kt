package com.example.moviebooking.data.remote.entities.tmdb.series

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieSearchingResultDTO(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("results") val results: List<ResultsItem?>? = null,
    @SerializedName("total_results") val totalResults: Int? = null
) : Parcelable

@Parcelize
data class ResultsItem(
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int?>? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("origin_country") val originCountry: List<String?>? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
) : Parcelable
