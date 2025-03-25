package com.example.moviebooking.domain.usecases.people.searchPeople

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleSearchingResultDTO
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class SearchPeopleUseCaseImpl(
    private val apiService: TMDBService
) : SearchPeopleUseCase, ApiHandler {
    override fun execute(query: String, page: Int): Flow<ResponseStatus<PeopleSearchingResultDTO>> {
        return flow {
            emit(handleApi { apiService.searchPeople(query = query, page = page) })
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