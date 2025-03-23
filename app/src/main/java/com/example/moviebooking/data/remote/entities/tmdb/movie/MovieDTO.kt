package com.example.moviebooking.data.remote.entities.tmdb.movie

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.moviebooking.data.local.entities.MovieEntity
import com.example.moviebooking.data.remote.entities.tmdb.Genre

@Parcelize
data class Movie(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null,
    @SerializedName("budget")
    val budget: Int? = null,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem?>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Int? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) : Parcelable {
    fun toEntity(): MovieEntity {
        return MovieEntity(
            id ?: "",
            adult,
            backdropPath,
            budget,
            homepage,
            imdbId,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            revenue,
            runtime,
            status,
            tagline,
            title,
            video,
            voteAverage,
            voteCount
        )
    }

    fun durationFormat(): String {
        val hours = runtime?.div(60)
        val minutes = runtime?.rem(60)
        return "${hours}h ${minutes}m"
    }
}

@Parcelize
data class SpokenLanguagesItem(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null
) : Parcelable

@Parcelize
data class ProductionCountriesItem(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
) : Parcelable

@Parcelize
data class ProductionCompaniesItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
) : Parcelable

@Parcelize
data class BelongsToCollection(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
) : Parcelable