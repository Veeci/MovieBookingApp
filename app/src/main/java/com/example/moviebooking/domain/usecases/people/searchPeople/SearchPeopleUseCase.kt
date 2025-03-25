package com.example.moviebooking.domain.usecases.people.searchPeople

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleSearchingResultDTO
import kotlinx.coroutines.flow.Flow

interface SearchPeopleUseCase {
    fun execute(query: String, page: Int): Flow<ResponseStatus<PeopleSearchingResultDTO>>
}