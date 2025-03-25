package com.example.moviebooking.domain.usecases.people.getSeriesCredits

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleDTO
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchSeriesCreditsUseCaseImpl(
    private val apiService: TMDBService
) : FetchSeriesCreditsUseCase, ApiHandler {
    override fun execute(personId: String): Flow<ResponseStatus<PeopleDTO>> {
        return flow {
            emit(handleApi { apiService.getTvCredits(personId) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Unknown Error",
                    errorCode = 500
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}