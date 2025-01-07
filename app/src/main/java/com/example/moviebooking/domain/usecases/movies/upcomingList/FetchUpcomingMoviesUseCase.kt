package com.example.moviebooking.domain.usecases.movies.upcomingList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import kotlinx.coroutines.flow.Flow

interface FetchUpcomingMoviesUseCase {
    fun execute(): Flow<ResponseStatus<MovieItem>>
}