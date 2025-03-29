package com.example.moviebooking.domain.usecases.movies.discover

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class DiscoverMoviesUseCaseImpl(
    private val apiService: TMDBService
) : DiscoverMoviesUseCase, ApiHandler {
    override fun execute(
        certification: String?,
        certificationGte: String?,
        certificationLte: String?,
        certificationCountry: String?,
        includeAdult: Boolean?,
        includeVideo: Boolean?,
        language: String?,
        page: Int?,
        primaryReleaseYear: Int?,
        primaryReleaseDateGte: String?,
        primaryReleaseDateLte: String?,
        region: String?,
        releaseDateGte: String?,
        releaseDateLte: String?,
        sortBy: String?,
        voteAverageGte: Float?,
        voteAverageLte: Float?,
        voteCountGte: Float?,
        voteCountLte: Float?,
        withCast: String?,
        withCompanies: String?,
        withCrew: String?,
        withGenres: String?,
        withKeywords: String?,
        withOriginCountry: String?,
        withOriginalLanguage: String?,
        withPeople: String?,
        withReleaseType: Int?,
        withRuntimeGte: Int?,
        withRuntimeLte: Int?,
        year: Int?
    ): Flow<ResponseStatus<MovieList>> {
        return flow {
            emit(handleApi { apiService.discoverMovies(
                certification,
                certificationGte,
                certificationLte,
                certificationCountry,
                includeAdult,
                includeVideo,
                language,
                page,
                primaryReleaseYear,
                primaryReleaseDateGte,
                primaryReleaseDateLte,
                region,
                releaseDateGte,
                releaseDateLte,
                sortBy,
                voteAverageGte,
                voteAverageLte,
                voteCountGte,
                voteCountLte,
                withCast,
                withCompanies,
                withCrew,
                withGenres,
                withKeywords,
                withOriginCountry,
                withOriginalLanguage,
                withPeople,
                withReleaseType,
                withRuntimeGte,
                withRuntimeLte,
                year
            ) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                e.message ?: "Unknown Error"
            ))
        }.flowOn(Dispatchers.IO)
    }
}