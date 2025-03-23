package com.example.moviebooking.data.remote.entities.tmdb.series

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieSeasonDTO(
    @SerializedName("air_date") val airDate: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("season_number") val seasonNumber: Int? = null,
    @SerializedName("_id") val _id: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("episodes") val episodes: List<EpisodesItem?>? = null,
    @SerializedName("poster_path") val posterPath: String? = null
) : Parcelable

@Parcelize
data class GuestStarsItem(
    @SerializedName("character") val character: String? = null,
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("order") val order: Int? = null
) : Parcelable

@Parcelize
data class SeasonCrewItem(
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("job") val job: String? = null,
    @SerializedName("department") val department: String? = null,
    @SerializedName("adult") val adult: Boolean? = null
) : Parcelable

@Parcelize
data class EpisodesItem(
    @SerializedName("episode_type") val episodeType: String? = null,
    @SerializedName("production_code") val productionCode: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("show_id") val showId: Int? = null,
    @SerializedName("season_number") val seasonNumber: Int? = null,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("still_path") val stillPath: String? = null,
    @SerializedName("crew") val crew: List<SeasonCrewItem?>? = null,
    @SerializedName("guest_stars") val guestStars: List<GuestStarsItem?>? = null,
    @SerializedName("air_date") val airDate: String? = null,
    @SerializedName("episode_number") val episodeNumber: Int? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
) : Parcelable
