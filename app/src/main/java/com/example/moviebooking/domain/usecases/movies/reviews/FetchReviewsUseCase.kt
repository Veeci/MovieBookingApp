package com.example.moviebooking.domain.usecases.movies.reviews

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Review
import kotlinx.coroutines.flow.Flow

interface FetchReviewsUseCase {
    fun fetchData(movieId: String): Flow<ResponseStatus<Review>>
}