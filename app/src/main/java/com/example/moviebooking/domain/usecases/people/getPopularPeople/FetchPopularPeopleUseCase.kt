package com.example.moviebooking.domain.usecases.people.getPopularPeople

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleItemDTO
import kotlinx.coroutines.flow.Flow

interface FetchPopularPeopleUseCase {
    fun execute(page: Int): Flow<ResponseStatus<PeopleItemDTO>>
}