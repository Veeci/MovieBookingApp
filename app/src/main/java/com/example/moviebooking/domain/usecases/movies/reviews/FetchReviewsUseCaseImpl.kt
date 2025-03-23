package com.example.moviebooking.domain.usecases.movies.reviews

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Review
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchReviewsUseCaseImpl(
    private val apiService: TMDBService
): FetchReviewsUseCase, ApiHandler {
    override fun fetchData(movieId: String): Flow<ResponseStatus<Review>> {
        return flow {
            emit(handleApi { apiService.getReviewList(movieId = movieId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                message = e.message ?: "Something went wrong"
            ))
        }.flowOn(Dispatchers.IO)
    }
}