package com.example.moviebooking.domain.usecases.series.similarList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SimilarSeries
import kotlinx.coroutines.flow.Flow

interface FetchSimilarSeriesUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<SimilarSeries>>
}