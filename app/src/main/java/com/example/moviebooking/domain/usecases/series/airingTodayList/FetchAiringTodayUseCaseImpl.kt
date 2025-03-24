package com.example.moviebooking.domain.usecases.series.airingTodayList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchAiringTodayUseCaseImpl(
    private val apiService: TMDBService
) : FetchAiringTodayUseCase, ApiHandler {
    override fun fetchData(page: Int?): Flow<ResponseStatus<SeriesList>> {
        return flow {
            emit(handleApi { apiService.getAiringTodaySeries(page = page) })
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