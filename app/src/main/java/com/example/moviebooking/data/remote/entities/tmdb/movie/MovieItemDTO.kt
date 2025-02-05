package com.example.moviebooking.data.remote.entities.tmdb.movie

import android.os.Parcelable
import com.example.baseproject.data.BaseDTO
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
	@SerializedName("dates")
	val dates: Dates? = null,
	@SerializedName("page")
	val page: Int? = null,
	@SerializedName("results")
	val results: List<Results?>? = null,
	@SerializedName("total_pages")
	val totalPages: Int? = null,
	@SerializedName("total_results")
	val totalResults: Int? = null
) : Parcelable, BaseDTO(){
	fun toMovieItemEntities(): List<MovieItemEntity> {
		return results?.mapNotNull { it?.toEntity() } ?: emptyList()
	}
}

@Parcelize
data class Results(
	@SerializedName("adult")
	val adult: Boolean? = null,
	@SerializedName("backdrop_path")
	val backdropPath: String? = null,
	@SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,
	@SerializedName("id")
	val id: Int? = null,
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
	@SerializedName("release_date")
	val releaseDate: String? = null,
	@SerializedName("title")
	val title: String? = null,
	@SerializedName("video")
	val video: Boolean? = null,
	@SerializedName("vote_average")
	val voteAverage: Double? = null,
	@SerializedName("vote_count")
	val voteCount: Int? = null
) : Parcelable {
	fun toEntity(): MovieItemEntity {
		return MovieItemEntity(
			id = this.id?.toString() ?: "",
			adult = this.adult,
			backdropPath = this.backdropPath,
			originalLanguage = this.originalLanguage,
			originalTitle = this.originalTitle,
			overview = this.overview,
			popularity = this.popularity,
			posterPath = this.posterPath,
			releaseDate = this.releaseDate,
			title = this.title,
			video = this.video,
			voteAverage = this.voteAverage,
			voteCount = this.voteCount
		)
	}
}

@Parcelize
data class Dates(
	@SerializedName("maximum")
	val maximum: String? = null,
	@SerializedName("minimum")
	val minimum: String? = null
) : Parcelable