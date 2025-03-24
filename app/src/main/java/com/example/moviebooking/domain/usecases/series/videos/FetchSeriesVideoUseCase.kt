package com.example.moviebooking.domain.usecases.series.videos

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Video
import kotlinx.coroutines.flow.Flow

interface FetchSeriesVideoUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<Video>>
}