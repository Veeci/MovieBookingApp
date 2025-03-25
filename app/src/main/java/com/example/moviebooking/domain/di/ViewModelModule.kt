package com.example.moviebooking.domain.di

import com.example.baseproject.domain.utils.journeyViewModel
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.domain.viewmodels.PeopleViewModel
import com.example.moviebooking.domain.viewmodels.SeriesViewModel
import org.koin.dsl.module

object ViewModelModule {
    fun init() = module {
        journeyViewModel { MovieViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
        journeyViewModel { SeriesViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
        journeyViewModel { PeopleViewModel(get(), get(), get(), get(), get(), get()) }
        journeyViewModel { BookingViewModel(get()) }
    }
}