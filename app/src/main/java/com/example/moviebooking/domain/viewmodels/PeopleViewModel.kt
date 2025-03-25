package com.example.moviebooking.domain.viewmodels

import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.domain.usecases.people.getMovieCredits.FetchMovieCreditsUseCase
import com.example.moviebooking.domain.usecases.people.getPersonDetail.FetchPersonDetailUseCase
import com.example.moviebooking.domain.usecases.people.getPersonImages.FetchPersonImagesUseCase
import com.example.moviebooking.domain.usecases.people.getPopularPeople.FetchPopularPeopleUseCase
import com.example.moviebooking.domain.usecases.people.getSeriesCredits.FetchSeriesCreditsUseCase
import com.example.moviebooking.domain.usecases.people.searchPeople.SearchPeopleUseCase

class PeopleViewModel(
    private val fetchPopularPeopleUseCase: FetchPopularPeopleUseCase,
    private val fetchPersonDetailUseCase: FetchPersonDetailUseCase,
    private val fetchPersonImagesUseCase: FetchPersonImagesUseCase,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase,
    private val fetchSeriesCreditsUseCase: FetchSeriesCreditsUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase
) : BaseViewModel() {
}