package com.example.moviebooking.domain.usecases.series.keywordList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchSeriesKeywordUseCaseImpl(
    private val apiService: TMDBService
) : FetchSeriesKeywordUseCase, ApiHandler {
    override fun execute(seriesId: String): Flow<ResponseStatus<Keyword>> {
        return flow {
            emit(handleApi { apiService.getSeriesKeywordList(seriesId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Something went wrong",
                    errorCode = 500
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}