package com.example.moviebooking.domain.usecases.series.reviews

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Review
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchSeriesReviewsUseCaseImpl(
    private val apiService: TMDBService
) : FetchSeriesReviewsUseCase, ApiHandler {
    override fun execute(seriesId: String): Flow<ResponseStatus<Review>> {
        return flow {
            emit(handleApi { apiService.getSeriesReviewList(seriesId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Unknown Error",
                    errorCode = 500
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}