package com.example.moviebooking.domain.usecases.movies.similarList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.SimilarMovie
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchSimilarMoviesUseCaseImpl(
    private val apiService: TMDBService
): FetchSimilarMoviesUseCase, ApiHandler {
    override fun fetchData(movieId: String): Flow<ResponseStatus<SimilarMovie>> {
        return flow {
            emit(handleApi { apiService.getSimilarMovies(movieId) })
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