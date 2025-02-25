package com.example.moviebooking.domain.usecases.movies.castList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.Credit
import com.example.moviebooking.data.remote.services.tmdb.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchCastListUseCaseImpl(
    private val apiService: MovieService,
) : FetchCastListUseCase, ApiHandler {
    override fun fetchData(movieId: String): Flow<ResponseStatus<Credit>> {
        return flow {
            try {
                emit(handleApi { apiService.getCastList(movieId) })
            } catch (e: Exception) {
                emit(ResponseStatus.Error(
                    message = e.message ?: "Unknown Error"
                ))
            }
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                message = e.message ?: "Unknown Error"
            ))
        }.flowOn(Dispatchers.IO)
    }
}