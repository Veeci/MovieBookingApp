package com.example.moviebooking.domain.usecases.movies.recommendedList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.RecommendationMovie
import kotlinx.coroutines.flow.Flow

interface FetchRecommendedMoviesUseCase {
    fun fetchData(movieId: String) : Flow<ResponseStatus<RecommendationMovie>>
}