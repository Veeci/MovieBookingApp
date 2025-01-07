package com.example.moviebooking.domain.di

import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.popularList.FetchPopularMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.popularList.FetchPopularMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.topRatedList.FetchTopRatedMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.topRatedList.FetchTopRatedMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.upcomingList.FetchUpcomingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.upcomingList.FetchUpcomingMoviesUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    fun init() = module{
        factory<FetchNowPlayingMoviesUseCase> { FetchNowPlayingMoviesUseCaseImpl(get()) }
        factory<FetchPopularMoviesUseCase> { FetchPopularMoviesUseCaseImpl(get()) }
        factory<FetchUpcomingMoviesUseCase> { FetchUpcomingMoviesUseCaseImpl(get()) }
        factory<FetchTopRatedMoviesUseCase> { FetchTopRatedMoviesUseCaseImpl(get()) }
    }
}