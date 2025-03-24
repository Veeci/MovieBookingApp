package com.example.moviebooking.domain.usecases.series.search

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import kotlinx.coroutines.flow.Flow

interface SearchSeriesUseCase {
    fun execute(query: String): Flow<ResponseStatus<SeriesList>>
}