package com.example.moviebooking.data.remote.entities.tmdb.people

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleDTO(
    @SerializedName("also_known_as") val alsoKnownAs: List<String>? = emptyList(),
    @SerializedName("birthday") val birthday: String? = null,
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("biography") val biography: String? = null,
    @SerializedName("deathday") val deathday: String? = null,
    @SerializedName("place_of_birth") val placeOfBirth: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("homepage") val homepage: String? = null
) : Parcelable