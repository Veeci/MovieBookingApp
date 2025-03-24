package com.example.moviebooking.data.remote.entities.tmdb.series

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int? = null,
    @SerializedName("networks") val networks: List<NetworksItem?>? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genres") val genres: List<GenresItem?>? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountriesItem?>? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("seasons") val seasons: List<SeasonsItem?>? = null,
    @SerializedName("languages") val languages: List<String?>? = null,
    @SerializedName("created_by") val createdBy: List<CreatedByItem?>? = null,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: LastEpisodeToAir? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("origin_country") val originCountry: List<String?>? = null,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguagesItem?>? = null,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompaniesItem?>? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("tagline") val tagline: String? = null,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int?>? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("next_episode_to_air") val nextEpisodeToAir: NextEpisodeToAir? = null,
    @SerializedName("in_production") val inProduction: Boolean? = null,
    @SerializedName("last_air_date") val lastAirDate: String? = null,
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("status") val status: String? = null
) : Parcelable

@Parcelize
data class SpokenLanguagesItem(
    @SerializedName("name") val name: String? = null,
    @SerializedName("iso_639_1") val iso6391: String? = null,
    @SerializedName("english_name") val englishName: String? = null
) : Parcelable

@Parcelize
data class SeasonsItem(
    @SerializedName("air_date") val airDate: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("episode_count") val episodeCount: Int? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("season_number") val seasonNumber: Int? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null
) : Parcelable

@Parcelize
data class ProductionCountriesItem(
    @SerializedName("iso_3166_1") val iso31661: String? = null,
    @SerializedName("name") val name: String? = null
) : Parcelable

@Parcelize
data class CreatedByItem(
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("id") val id: Int? = null
) : Parcelable

@Parcelize
data class LastEpisodeToAir(
    @SerializedName("episode_type") val episodeType: String? = null,
    @SerializedName("production_code") val productionCode: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("show_id") val showId: Int? = null,
    @SerializedName("season_number") val seasonNumber: Int? = null,
    @SerializedName("runtime") val runtime: Double? = null,
    @SerializedName("still_path") val stillPath: String? = null,
    @SerializedName("air_date") val airDate: String? = null,
    @SerializedName("episode_number") val episodeNumber: Int? = null,
    @SerializedName("vote_average") val voteAverage: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
) : Parcelable

@Parcelize
data class ProductionCompaniesItem(
    @SerializedName("logo_path") val logoPath: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("origin_country") val originCountry: String? = null
) : Parcelable

@Parcelize
data class NextEpisodeToAir(
    @SerializedName("episode_type") val episodeType: String? = null,
    @SerializedName("production_code") val productionCode: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("show_id") val showId: Int? = null,
    @SerializedName("season_number") val seasonNumber: Int? = null,
    @SerializedName("runtime") val runtime: Double? = null,
    @SerializedName("still_path") val stillPath: String? = null,
    @SerializedName("air_date") val airDate: String? = null,
    @SerializedName("episode_number") val episodeNumber: Int? = null,
    @SerializedName("vote_average") val voteAverage: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
) : Parcelable

@Parcelize
data class GenresItem(
    @SerializedName("name") val name: String? = null, @SerializedName("id") val id: Int? = null
) : Parcelable

@Parcelize
data class NetworksItem(
    @SerializedName("logo_path") val logoPath: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("origin_country") val originCountry: String? = null
) : Parcelable