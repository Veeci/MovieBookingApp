package com.example.moviebooking.domain.usecases.movies.movieDetail

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.entities.MovieEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import kotlinx.coroutines.flow.Flow

interface FetchMovieDetailUseCase {
    fun fetchData(movieID: String): Flow<ResponseStatus<Movie>>

    fun saveData(movie: MovieEntity): Flow<Boolean>

    fun getFromLocal(movieID: String): Flow<MovieEntity>
}