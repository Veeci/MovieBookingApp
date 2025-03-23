package com.example.moviebooking.domain.usecases.movies.images

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Image
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class FetchImageUseCaseImpl(
    private val apiService: TMDBService
): FetchImageUseCase, ApiHandler {
    override fun fetchData(movieId: String): Flow<ResponseStatus<Image>> {
        return flow {
            emit(handleApi { apiService.getImageList(movieId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Unknown Error"
                )
            )
        }.onStart { Dispatchers.IO }
    }
}