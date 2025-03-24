package com.example.moviebooking.domain.usecases.series.genreList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Genres
import kotlinx.coroutines.flow.Flow

interface FetchGenreListUseCase {
    fun execute(): Flow<ResponseStatus<Genres>>
}