package com.example.moviebooking.domain.usecases.series.episodeDetail

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieEpisodeDTO
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchSeriesEpisodeDetailUseCaseImpl(
    private val apiService: TMDBService
) : FetchSeriesEpisodeDetailUseCase, ApiHandler {
    override fun execute(
        seriesId: String,
        seasonNumber: String,
        episodeNumber: String
    ): Flow<ResponseStatus<SerieEpisodeDTO>> {
        return flow {
            emit(handleApi { apiService.getEpisodeDetail(seriesId, seasonNumber, episodeNumber) })
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