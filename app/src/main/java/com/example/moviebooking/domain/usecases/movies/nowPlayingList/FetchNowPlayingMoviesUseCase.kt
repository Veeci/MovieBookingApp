package com.example.moviebooking.domain.usecases.movies.nowPlayingList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import kotlinx.coroutines.flow.Flow

interface FetchNowPlayingMoviesUseCase {
    fun execute(): Flow<ResponseStatus<MovieItem>>
}