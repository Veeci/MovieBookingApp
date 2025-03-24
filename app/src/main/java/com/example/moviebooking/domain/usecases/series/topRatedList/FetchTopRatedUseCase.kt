package com.example.moviebooking.domain.usecases.series.topRatedList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import kotlinx.coroutines.flow.Flow

interface FetchTopRatedUseCase {
    fun execute(page: Int?): Flow<ResponseStatus<SeriesList>>
}