package com.example.moviebooking.domain.usecases.movies.castList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.Credit
import kotlinx.coroutines.flow.Flow

interface FetchCastListUseCase {
    fun fetchData(movieId: String): Flow<ResponseStatus<Credit>>
}