package com.example.moviebooking.domain.usecases.series.onTheAirList

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

class FetchOnTheAirUseCaseImpl(
    private val apiService: TMDBService
) : FetchOnTheAirUseCase, ApiHandler {
    override fun execute(page: Int?): Flow<ResponseStatus<SeriesList>> {
        return flow {
            emit(handleApi { apiService.getOnTheAirSeries(page = page) })
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