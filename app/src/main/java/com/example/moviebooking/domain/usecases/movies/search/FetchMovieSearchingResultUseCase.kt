package com.example.moviebooking.domain.usecases.movies.search

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieSearchResult
import kotlinx.coroutines.flow.Flow

interface FetchMovieSearchingResultUseCase {
    fun fetchData(query: String) : Flow<ResponseStatus<MovieSearchResult>>
}