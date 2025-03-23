package com.example.moviebooking.domain.usecases.movies.getKeywords

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchKeywordUseCaseImpl(
    private val apiService: TMDBService,
): FetchKeywordUseCase, ApiHandler {
    override suspend fun fetchData(movieId: String): Flow<ResponseStatus<Keyword>> {
        return flow {
            emit(handleApi { apiService.getKeywordList(movieId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                message = e.message ?: "Unknown Error",
            ))
        }.flowOn(Dispatchers.IO)
    }
}