package com.example.moviebooking.domain.usecases.movies.search

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieSearchResultDTO
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchMovieSearchingResultUseCaseImpl(
    private val apiService: TMDBService
): FetchMovieSearchingResultUseCase, ApiHandler {
    override fun fetchData(query: String): Flow<ResponseStatus<MovieSearchResultDTO>> {
        return flow {
            emit(handleApi { apiService.searchMovies(query) })
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