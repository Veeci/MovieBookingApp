package com.example.moviebooking.domain.usecases.series.recommendedList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.RecommendationSeries
import kotlinx.coroutines.flow.Flow

interface FetchRecommendationSeriesUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<RecommendationSeries>>
}