package com.example.moviebooking.domain.usecases.movies.recommendedList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.RecommendationMovie
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchRecommendedMoviesUseCaseImpl(
    private val apiService: TMDBService
): FetchRecommendedMoviesUseCase, ApiHandler {
    override fun fetchData(movieId: String): Flow<ResponseStatus<RecommendationMovie>> {
        return flow {
            emit(handleApi { apiService.getRecommendationMovies(movieId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Unknown Error"
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}