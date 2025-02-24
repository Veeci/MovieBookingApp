package com.example.moviebooking.domain.di

import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCase
import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.genreList.FetchGenreListUseCase
import com.example.moviebooking.domain.usecases.movies.genreList.FetchGenreListUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.getKeywords.FetchKeywordUseCase
import com.example.moviebooking.domain.usecases.movies.getKeywords.FetchKeywordUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.movieDetail.FetchMovieDetailUseCase
import com.example.moviebooking.domain.usecases.movies.movieDetail.FetchMovieDetailUseCaseImpl
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
        factory<FetchNowPlayingMoviesUseCase> { FetchNowPlayingMoviesUseCaseImpl(get(), get()) }
        factory<FetchPopularMoviesUseCase> { FetchPopularMoviesUseCaseImpl(get(), get()) }
        factory<FetchUpcomingMoviesUseCase> { FetchUpcomingMoviesUseCaseImpl(get(), get()) }
        factory<FetchTopRatedMoviesUseCase> { FetchTopRatedMoviesUseCaseImpl(get(), get()) }
        factory<FetchGenreListUseCase> { FetchGenreListUseCaseImpl(get(), get()) }
        factory<FetchMovieDetailUseCase> { FetchMovieDetailUseCaseImpl(get(), get()) }
        factory<FetchCastListUseCase> { FetchCastListUseCaseImpl(get()) }
        factory<FetchKeywordUseCase> { FetchKeywordUseCaseImpl(get()) }
    }
}