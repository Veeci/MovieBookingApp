package com.example.moviebooking.domain.usecases.series.recommendedList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.RecommendationSeries
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchRecommendationSeriesUseCaseImpl(
    private val apiService: TMDBService
) : FetchRecommendationSeriesUseCase, ApiHandler {
    override fun execute(seriesId: String): Flow<ResponseStatus<RecommendationSeries>> {
        return flow {
            emit(handleApi { apiService.getRecommendationSeries(seriesId) })
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