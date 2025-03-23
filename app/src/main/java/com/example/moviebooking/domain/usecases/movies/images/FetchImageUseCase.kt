package com.example.moviebooking.domain.usecases.movies.images

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Image
import kotlinx.coroutines.flow.Flow

interface FetchImageUseCase {
    fun fetchData(movieId: String) : Flow<ResponseStatus<Image>>
}