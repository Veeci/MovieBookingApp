package com.example.moviebooking.domain.usecases.people.getPersonDetail

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleDTO
import kotlinx.coroutines.flow.Flow

interface FetchPersonDetailUseCase {
    fun execute(personId: String): Flow<ResponseStatus<PeopleDTO>>

}