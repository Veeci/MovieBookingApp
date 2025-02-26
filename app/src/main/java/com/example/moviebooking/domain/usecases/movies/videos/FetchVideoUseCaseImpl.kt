package com.example.moviebooking.domain.usecases.movies.videos

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.Video
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchVideoUseCaseImpl(
    private val apiService: TMDBService
): FetchVideoUseCase, ApiHandler {
    override fun fetchData(movieId: String): Flow<ResponseStatus<Video>> {
        return  flow{
            emit(handleApi { apiService.getVideoList(movieId) })
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