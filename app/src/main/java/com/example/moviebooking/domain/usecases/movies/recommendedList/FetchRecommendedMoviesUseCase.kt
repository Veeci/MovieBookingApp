package com.example.moviebooking.domain.usecases.movies.recommendedList

import RecommendationMovie
import com.example.baseproject.domain.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface FetchRecommendedMoviesUseCase {
    fun fetchData(movieId: String) : Flow<ResponseStatus<RecommendationMovie>>
}