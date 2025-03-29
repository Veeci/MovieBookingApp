package com.example.moviebooking.domain.usecases.series.seasonDetail

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieSeasonDTO
import kotlinx.coroutines.flow.Flow

interface FetchSeriesSeasonDetailUseCase {
    fun execute(seriesId: String, seasonNumber: Int): Flow<ResponseStatus<SerieSeasonDTO>>
}