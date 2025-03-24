package com.example.moviebooking.domain.usecases.series.videos

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Video
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class FetchSeriesVideoUseCaseImpl(
    private val apiService: TMDBService
) : FetchSeriesVideoUseCase, ApiHandler {
    override fun execute(seriesId: String): Flow<ResponseStatus<Video>> {
        return flow {
            emit(handleApi { apiService.getSeriesVideoList(seriesId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Unknown Error",
                    errorCode = 500
                )
            )
        }
    }
}