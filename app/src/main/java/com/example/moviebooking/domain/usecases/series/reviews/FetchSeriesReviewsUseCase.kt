package com.example.moviebooking.domain.usecases.series.reviews

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Review
import kotlinx.coroutines.flow.Flow

interface FetchSeriesReviewsUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<Review>>
}