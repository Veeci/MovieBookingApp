package com.example.moviebooking.domain.usecases.series.seriesDetail

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import kotlinx.coroutines.flow.Flow

interface FetchSeriesDetailUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<Series>>
}