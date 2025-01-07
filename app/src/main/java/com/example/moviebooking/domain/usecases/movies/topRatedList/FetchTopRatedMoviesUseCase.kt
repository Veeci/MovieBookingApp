package com.example.moviebooking.domain.usecases.movies.topRatedList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import kotlinx.coroutines.flow.Flow

interface FetchTopRatedMoviesUseCase {
    fun execute(): Flow<ResponseStatus<MovieItem>>
}