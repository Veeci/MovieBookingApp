package com.example.moviebooking.domain.usecases.movies.getKeywords

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import kotlinx.coroutines.flow.Flow

interface FetchKeywordUseCase {
    suspend fun fetchData(movieId: String): Flow<ResponseStatus<Keyword>>
}