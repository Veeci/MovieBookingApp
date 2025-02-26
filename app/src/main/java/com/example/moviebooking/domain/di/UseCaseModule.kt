package com.example.moviebooking.domain.di

import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCase
import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.genreList.FetchGenreListUseCase
import com.example.moviebooking.domain.usecases.movies.genreList.FetchGenreListUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.getKeywords.FetchKeywordUseCase
import com.example.moviebooking.domain.usecases.movies.getKeywords.FetchKeywordUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.images.FetchImageUseCase
import com.example.moviebooking.domain.usecases.movies.images.FetchImageUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.movieDetail.FetchMovieDetailUseCase
import com.example.moviebooking.domain.usecases.movies.movieDetail.FetchMovieDetailUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.popularList.FetchPopularMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.popularList.FetchPopularMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.recommendedList.FetchRecommendedMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.recommendedList.FetchRecommendedMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.reviews.FetchReviewsUseCase
import com.example.moviebooking.domain.usecases.movies.reviews.FetchReviewsUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.search.FetchMovieSearchingResultUseCase
import com.example.moviebooking.domain.usecases.movies.search.FetchMovieSearchingResultUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.similarList.FetchSimilarMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.similarList.FetchSimilarMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.topRatedList.FetchTopRatedMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.topRatedList.FetchTopRatedMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.upcomingList.FetchUpcomingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.upcomingList.FetchUpcomingMoviesUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.videos.FetchVideoUseCase
import com.example.moviebooking.domain.usecases.movies.videos.FetchVideoUseCaseImpl
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
        factory<FetchSimilarMoviesUseCase> { FetchSimilarMoviesUseCaseImpl(get()) }
        factory<FetchRecommendedMoviesUseCase> { FetchRecommendedMoviesUseCaseImpl(get()) }
        factory<FetchImageUseCase> { FetchImageUseCaseImpl(get()) }
        factory<FetchVideoUseCase> { FetchVideoUseCaseImpl(get()) }
        factory<FetchMovieSearchingResultUseCase> { FetchMovieSearchingResultUseCaseImpl(get()) }
        factory<FetchReviewsUseCase> { FetchReviewsUseCaseImpl(get()) }
    }
}