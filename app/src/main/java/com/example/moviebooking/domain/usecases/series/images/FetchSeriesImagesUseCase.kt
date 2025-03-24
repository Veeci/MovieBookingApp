package com.example.moviebooking.domain.usecases.series.images

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Image
import kotlinx.coroutines.flow.Flow

interface FetchSeriesImagesUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<Image>>
}