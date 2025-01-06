package com.example.moviebooking.domain.di

import com.example.moviebooking.domain.usecases.movies.FetchNowPlayingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.FetchNowPlayingMoviesUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    fun init() = module{
        factory<FetchNowPlayingMoviesUseCase> { FetchNowPlayingMoviesUseCaseImpl(get()) }
    }
}