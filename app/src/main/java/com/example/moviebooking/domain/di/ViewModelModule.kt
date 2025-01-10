package com.example.moviebooking.domain.di

import com.example.baseproject.domain.utils.screenViewModel
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import org.koin.dsl.module

object ViewModelModule {
    fun init() = module {
        screenViewModel { MovieViewModel(get(), get(), get(), get()) }
    }
}