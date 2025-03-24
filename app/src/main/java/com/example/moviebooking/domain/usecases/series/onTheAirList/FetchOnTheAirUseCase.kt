package com.example.moviebooking.domain.usecases.series.onTheAirList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import kotlinx.coroutines.flow.Flow

interface FetchOnTheAirUseCase {
    fun execute(page: Int?): Flow<ResponseStatus<SeriesList>>
}