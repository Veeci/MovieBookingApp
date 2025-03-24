package com.example.moviebooking.domain.usecases.series.airingTodayList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import kotlinx.coroutines.flow.Flow

interface FetchAiringTodayUseCase {
    fun fetchData(page: Int?) : Flow<ResponseStatus<SeriesList>>
}