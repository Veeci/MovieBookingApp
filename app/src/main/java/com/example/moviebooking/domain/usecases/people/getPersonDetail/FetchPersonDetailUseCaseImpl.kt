package com.example.moviebooking.domain.usecases.people.getPersonDetail

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

class FetchPersonDetailUseCaseImpl(
    private val apiService: TMDBService
) : FetchPersonDetailUseCase, ApiHandler {
    override fun execute(personId: String): Flow<ResponseStatus<PeopleDTO>> {
        return flow {
            emit(handleApi { apiService.getPersonDetail(personId = personId) })
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