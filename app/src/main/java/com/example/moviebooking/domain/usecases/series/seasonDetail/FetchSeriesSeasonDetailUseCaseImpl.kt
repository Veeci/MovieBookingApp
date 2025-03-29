package com.example.moviebooking.domain.usecases.series.seasonDetail

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieSeasonDTO
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchSeriesSeasonDetailUseCaseImpl(
    private val apiService: TMDBService
) : FetchSeriesSeasonDetailUseCase, ApiHandler {
    override fun execute(
        seriesId: String,
        seasonNumber: Int
    ): Flow<ResponseStatus<SerieSeasonDTO>> {
        return flow {
            emit(handleApi { apiService.getSeasonDetail(seriesId, seasonNumber) })
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