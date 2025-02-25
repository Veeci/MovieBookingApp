package com.example.moviebooking.domain.di

import com.example.baseproject.domain.utils.journeyViewModel
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import org.koin.dsl.module

object ViewModelModule {
    fun init() = module {
        journeyViewModel { MovieViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    }
}