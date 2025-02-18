package com.example.moviebooking.domain.usecases.movies.topRatedList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import kotlinx.coroutines.flow.Flow

interface FetchTopRatedMoviesUseCase {
    fun fetchData(): Flow<ResponseStatus<MovieList>>

    fun saveData(list: List<MovieItemEntity>): Flow<Boolean>

    fun getFromLocal(): Flow<List<MovieItemEntity>>
}