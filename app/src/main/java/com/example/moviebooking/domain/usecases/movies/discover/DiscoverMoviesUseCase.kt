package com.example.moviebooking.domain.usecases.movies.discover

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import kotlinx.coroutines.flow.Flow

interface DiscoverMoviesUseCase {
    fun execute(
        certification: String? = null,
        certificationGte: String? = null,
        certificationLte: String? = null,
        certificationCountry: String? = null,
        includeAdult: Boolean? = false,
        includeVideo: Boolean? = false,
        language: String? = "en-US",
        page: Int? = 1,
        primaryReleaseYear: Int? = null,
        primaryReleaseDateGte: String? = null,
        primaryReleaseDateLte: String? = null,
        region: String? = null,
        releaseDateGte: String? = null,
        releaseDateLte: String? = null,
        sortBy: String? = "popularity.desc",
        voteAverageGte: Float? = null,
        voteAverageLte: Float? = null,
        voteCountGte: Float? = null,
        voteCountLte: Float? = null,
        withCast: String? = null,
        withCompanies: String? = null,
        withCrew: String? = null,
        withGenres: String? = null,
        withKeywords: String? = null,
        withOriginCountry: String? = null,
        withOriginalLanguage: String? = null,
        withPeople: String? = null,
        withReleaseType: Int? = null,
        withRuntimeGte: Int? = null,
        withRuntimeLte: Int? = null,
        year: Int? = null
        ): Flow<ResponseStatus<MovieList>>
}