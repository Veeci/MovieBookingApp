package com.example.moviebooking.domain.usecases.movies.similarList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.SimilarMovie
import kotlinx.coroutines.flow.Flow

interface FetchSimilarMoviesUseCase {
    fun fetchData(movieId: String) : Flow<ResponseStatus<SimilarMovie>>
}