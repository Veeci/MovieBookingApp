package com.example.moviebooking.domain.usecases.movies.genreList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.entities.MovieGenreEntity
import com.example.moviebooking.data.remote.entities.tmdb.Genres
import kotlinx.coroutines.flow.Flow

interface FetchGenreListUseCase {
    fun fetchData(): Flow<ResponseStatus<Genres>>

    fun saveData(list: List<MovieGenreEntity>): Flow<Boolean>

    fun getFromLocal(): Flow<List<MovieGenreEntity>>
}