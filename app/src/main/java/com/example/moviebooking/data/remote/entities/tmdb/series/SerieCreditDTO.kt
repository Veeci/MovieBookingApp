package com.example.moviebooking.data.remote.entities.tmdb.series

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieCreditDTO(
    @SerializedName("cast") val cast: List<CastItem?>? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("crew") val crew: List<SeasonCrewItem?>? = null
) : Parcelable

@Parcelize
data class RolesItem(
    @SerializedName("character") val character: String? = null,
    @SerializedName("episode_count") val episodeCount: Int? = null,
    @SerializedName("credit_id") val creditId: String? = null
) : Parcelable

@Parcelize
data class CastItem(
    @SerializedName("total_episode_count") val totalEpisodeCount: Int? = null,
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("roles") val roles: List<RolesItem?>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("order") val order: Int? = null
) : Parcelable

@Parcelize
data class JobsItem(
    @SerializedName("episode_count") val episodeCount: Int? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("job") val job: String? = null
) : Parcelable

@Parcelize
data class CrewItem(
    @SerializedName("total_episode_count") val totalEpisodeCount: Int? = null,
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("jobs") val jobs: List<JobsItem?>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("department") val department: String? = null
) : Parcelable
