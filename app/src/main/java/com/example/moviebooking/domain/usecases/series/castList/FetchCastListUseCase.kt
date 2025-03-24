package com.example.moviebooking.domain.usecases.series.castList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.Credit
import kotlinx.coroutines.flow.Flow

interface FetchCastListUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<Credit>>
}