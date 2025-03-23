package com.example.moviebooking.data.remote.entities.tmdb.series

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SerieEpisodeDTO(
	@SerializedName("production_code") val productionCode: String? = null,
	@SerializedName("overview") val overview: String? = null,
	@SerializedName("season_number") val seasonNumber: Int? = null,
	@SerializedName("runtime") val runtime: Int? = null,
	@SerializedName("still_path") val stillPath: String? = null,
	@SerializedName("crew") val crew: List<EpisodeCrewItem?>? = null,
	@SerializedName("guest_stars") val guestStars: List<EpisodeGuestStarsItem?>? = null,
	@SerializedName("air_date") val airDate: String? = null,
	@SerializedName("episode_number") val episodeNumber: Int? = null,
	@SerializedName("vote_average") val voteAverage: Double? = null,
	@SerializedName("name") val name: String? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("vote_count") val voteCount: Int? = null
) : Parcelable

@Parcelize
data class EpisodeCrewItem(
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
data class EpisodeGuestStarsItem(
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
