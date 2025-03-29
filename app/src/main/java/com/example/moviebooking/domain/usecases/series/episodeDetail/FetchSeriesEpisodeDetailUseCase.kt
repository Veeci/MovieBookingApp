package com.example.moviebooking.domain.usecases.series.episodeDetail

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieEpisodeDTO
import kotlinx.coroutines.flow.Flow

interface FetchSeriesEpisodeDetailUseCase {
    fun execute(seriesId: String, seasonNumber: Int, episodeNumber: Int): Flow<ResponseStatus<SerieEpisodeDTO>>
}