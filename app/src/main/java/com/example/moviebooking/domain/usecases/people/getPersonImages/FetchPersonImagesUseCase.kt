package com.example.moviebooking.domain.usecases.people.getPersonImages

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Image
import kotlinx.coroutines.flow.Flow

interface FetchPersonImagesUseCase {
    fun execute(personId: String): Flow<ResponseStatus<Image>>
}