package com.example.moviebooking.domain.usecases.movies.videos

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.Video
import kotlinx.coroutines.flow.Flow

interface FetchVideoUseCase {
    fun fetchData(movieId: String) : Flow<ResponseStatus<Video>>
}