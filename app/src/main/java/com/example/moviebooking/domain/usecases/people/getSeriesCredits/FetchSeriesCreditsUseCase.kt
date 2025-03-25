package com.example.moviebooking.domain.usecases.people.getSeriesCredits

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleDTO
import kotlinx.coroutines.flow.Flow

interface FetchSeriesCreditsUseCase {
    fun execute(personId: String) : Flow<ResponseStatus<PeopleDTO>>
}