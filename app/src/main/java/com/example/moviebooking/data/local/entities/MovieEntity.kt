package com.example.moviebooking.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.baseproject.data.BaseEntity

@Entity(tableName = "Movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override var id: String,

    @ColumnInfo(name = "adult")
    val adult: Boolean? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "budget")
    val budget: Int? = null,

    @ColumnInfo(name = "homepage")
    val homepage: String? = null,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String? = null,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "revenue")
    val revenue: Int? = null,

    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "tagline")
    val tagline: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "video")
    val video: Boolean? = null,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null
) : BaseEntity(id)