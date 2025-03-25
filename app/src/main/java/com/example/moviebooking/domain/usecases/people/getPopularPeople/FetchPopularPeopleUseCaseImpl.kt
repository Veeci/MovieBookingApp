package com.example.moviebooking.domain.usecases.people.getPopularPeople

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleItemDTO
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchPopularPeopleUseCaseImpl(
    private val apiService: TMDBService
) : FetchPopularPeopleUseCase, ApiHandler {
    override fun execute(page: Int): Flow<ResponseStatus<PeopleItemDTO>> {
        return flow {
            emit(handleApi { apiService.getPopularPeople(page = page) })
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