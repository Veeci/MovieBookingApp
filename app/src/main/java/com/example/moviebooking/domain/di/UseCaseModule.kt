package com.example.moviebooking.domain.di

import com.example.moviebooking.domain.usecases.booking.BookingUseCase
import com.example.moviebooking.domain.usecases.booking.BookingUseCaseImpl
import com.example.moviebooking.domain.usecases.chat.ChatbotUseCase
import com.example.moviebooking.domain.usecases.chat.ChatbotUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCase
import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCaseImpl
import com.example.moviebooking.domain.usecases.movies.discover.DiscoverMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.discover.DiscoverMoviesUseCaseImpl
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
import com.example.moviebooking.domain.usecases.people.getMovieCredits.FetchMovieCreditsUseCase
import com.example.moviebooking.domain.usecases.people.getMovieCredits.FetchMovieCreditsUseCaseImpl
import com.example.moviebooking.domain.usecases.people.getPersonDetail.FetchPersonDetailUseCase
import com.example.moviebooking.domain.usecases.people.getPersonDetail.FetchPersonDetailUseCaseImpl
import com.example.moviebooking.domain.usecases.people.getPersonImages.FetchPersonImagesUseCase
import com.example.moviebooking.domain.usecases.people.getPersonImages.FetchPersonImagesUseCaseImpl
import com.example.moviebooking.domain.usecases.people.getPopularPeople.FetchPopularPeopleUseCase
import com.example.moviebooking.domain.usecases.people.getPopularPeople.FetchPopularPeopleUseCaseImpl
import com.example.moviebooking.domain.usecases.people.getSeriesCredits.FetchSeriesCreditsUseCase
import com.example.moviebooking.domain.usecases.people.getSeriesCredits.FetchSeriesCreditsUseCaseImpl
import com.example.moviebooking.domain.usecases.people.searchPeople.SearchPeopleUseCase
import com.example.moviebooking.domain.usecases.people.searchPeople.SearchPeopleUseCaseImpl
import com.example.moviebooking.domain.usecases.series.airingTodayList.FetchAiringTodayUseCase
import com.example.moviebooking.domain.usecases.series.airingTodayList.FetchAiringTodayUseCaseImpl
import com.example.moviebooking.domain.usecases.series.castList.FetchSeriesCastListUseCase
import com.example.moviebooking.domain.usecases.series.castList.FetchSeriesCastListUseCaseImpl
import com.example.moviebooking.domain.usecases.series.disccoverSeries.DiscoverSeriesUseCase
import com.example.moviebooking.domain.usecases.series.disccoverSeries.DiscoverSeriesUseCaseImpl
import com.example.moviebooking.domain.usecases.series.episodeDetail.FetchSeriesEpisodeDetailUseCase
import com.example.moviebooking.domain.usecases.series.episodeDetail.FetchSeriesEpisodeDetailUseCaseImpl
import com.example.moviebooking.domain.usecases.series.genreList.FetchSeriesGenreListUseCase
import com.example.moviebooking.domain.usecases.series.genreList.FetchSeriesGenreListUseCaseImpl
import com.example.moviebooking.domain.usecases.series.images.FetchSeriesImagesUseCase
import com.example.moviebooking.domain.usecases.series.images.FetchSeriesImagesUseCaseImpl
import com.example.moviebooking.domain.usecases.series.keywordList.FetchSeriesKeywordUseCase
import com.example.moviebooking.domain.usecases.series.keywordList.FetchSeriesKeywordUseCaseImpl
import com.example.moviebooking.domain.usecases.series.onTheAirList.FetchOnTheAirUseCase
import com.example.moviebooking.domain.usecases.series.onTheAirList.FetchOnTheAirUseCaseImpl
import com.example.moviebooking.domain.usecases.series.popularList.FetchPopularListUseCase
import com.example.moviebooking.domain.usecases.series.popularList.FetchPopularListUseCaseImpl
import com.example.moviebooking.domain.usecases.series.recommendedList.FetchRecommendationSeriesUseCase
import com.example.moviebooking.domain.usecases.series.recommendedList.FetchRecommendationSeriesUseCaseImpl
import com.example.moviebooking.domain.usecases.series.reviews.FetchSeriesReviewsUseCase
import com.example.moviebooking.domain.usecases.series.reviews.FetchSeriesReviewsUseCaseImpl
import com.example.moviebooking.domain.usecases.series.search.SearchSeriesUseCase
import com.example.moviebooking.domain.usecases.series.search.SearchSeriesUseCaseImpl
import com.example.moviebooking.domain.usecases.series.seasonDetail.FetchSeriesSeasonDetailUseCase
import com.example.moviebooking.domain.usecases.series.seasonDetail.FetchSeriesSeasonDetailUseCaseImpl
import com.example.moviebooking.domain.usecases.series.seriesDetail.FetchSeriesDetailUseCase
import com.example.moviebooking.domain.usecases.series.seriesDetail.FetchSeriesDetailUseCaseImpl
import com.example.moviebooking.domain.usecases.series.similarList.FetchSimilarSeriesUseCase
import com.example.moviebooking.domain.usecases.series.similarList.FetchSimilarSeriesUseCaseImpl
import com.example.moviebooking.domain.usecases.series.topRatedList.FetchTopRatedUseCase
import com.example.moviebooking.domain.usecases.series.topRatedList.FetchTopRatedUseCaseImpl
import com.example.moviebooking.domain.usecases.series.videos.FetchSeriesVideoUseCase
import com.example.moviebooking.domain.usecases.series.videos.FetchSeriesVideoUseCaseImpl
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
        factory<BookingUseCase> { BookingUseCaseImpl(get()) }
        factory<DiscoverMoviesUseCase> { DiscoverMoviesUseCaseImpl(get()) }

        factory<FetchAiringTodayUseCase> { FetchAiringTodayUseCaseImpl(get()) }
        factory<FetchOnTheAirUseCase> { FetchOnTheAirUseCaseImpl(get()) }
        factory<FetchTopRatedUseCase> { FetchTopRatedUseCaseImpl(get()) }
        factory<FetchPopularListUseCase> { FetchPopularListUseCaseImpl(get()) }
        factory<FetchSeriesCastListUseCase> { FetchSeriesCastListUseCaseImpl(get()) }
        factory<FetchSeriesEpisodeDetailUseCase> { FetchSeriesEpisodeDetailUseCaseImpl(get()) }
        factory<FetchSeriesSeasonDetailUseCase> { FetchSeriesSeasonDetailUseCaseImpl(get()) }
        factory<FetchSeriesGenreListUseCase> { FetchSeriesGenreListUseCaseImpl(get()) }
        factory<FetchSeriesImagesUseCase> { FetchSeriesImagesUseCaseImpl(get()) }
        factory<FetchSeriesKeywordUseCase> { FetchSeriesKeywordUseCaseImpl(get()) }
        factory<FetchRecommendationSeriesUseCase> { FetchRecommendationSeriesUseCaseImpl(get()) }
        factory<FetchSeriesVideoUseCase> { FetchSeriesVideoUseCaseImpl(get()) }
        factory<FetchSeriesReviewsUseCase> { FetchSeriesReviewsUseCaseImpl(get()) }
        factory<SearchSeriesUseCase> { SearchSeriesUseCaseImpl(get()) }
        factory<FetchSeriesDetailUseCase> { FetchSeriesDetailUseCaseImpl(get()) }
        factory<FetchSimilarSeriesUseCase> { FetchSimilarSeriesUseCaseImpl(get()) }
        factory<DiscoverSeriesUseCase> { DiscoverSeriesUseCaseImpl(get()) }

        factory<FetchPopularPeopleUseCase> { FetchPopularPeopleUseCaseImpl(get()) }
        factory<FetchPersonDetailUseCase> { FetchPersonDetailUseCaseImpl(get()) }
        factory<FetchMovieCreditsUseCase> { FetchMovieCreditsUseCaseImpl(get()) }
        factory<FetchSeriesCreditsUseCase> { FetchSeriesCreditsUseCaseImpl(get()) }
        factory<SearchPeopleUseCase> { SearchPeopleUseCaseImpl(get()) }
        factory<FetchPersonImagesUseCase> { FetchPersonImagesUseCaseImpl(get()) }

        factory<ChatbotUseCase> { ChatbotUseCaseImpl(get()) }
    }
}