package com.example.moviebooking.data.remote.services.tmdb

import com.example.moviebooking.data.remote.entities.tmdb.Genres
import com.example.moviebooking.data.remote.entities.tmdb.Image
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import com.example.moviebooking.data.remote.entities.tmdb.Review
import com.example.moviebooking.data.remote.entities.tmdb.Video
import com.example.moviebooking.data.remote.entities.tmdb.Credit
import com.example.moviebooking.data.remote.entities.tmdb.series.RecommendationSeries
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieEpisodeDTO
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieSeasonDTO
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import com.example.moviebooking.data.remote.entities.tmdb.series.SimilarSeries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {
    @GET("tv/airing_today")
    suspend fun getAiringTodaySeries(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SeriesList>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirSeries(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SeriesList>

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SeriesList>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SeriesList>

    @GET("tv/{series_id}")
    suspend fun getSeriesDetail(
        @Path("series_id") seriesId: String
    ): Response<Series>

    @GET("genre/tv/list")
    suspend fun getSeriesGenreList(
        @Query("language") language: String? = "en"
    ): Response<Genres>

    @GET("tv/{series_id}/credits")
    suspend fun getGenreCastList(
        @Path("series_id") seriesId: String
    ): Response<Credit>

    @GET("tv/series_id/keywords")
    suspend fun getSeriesKeywordList(
        @Path("series_id") seriesId: String
    ): Response<Keyword>

    @GET("tv/{series_id}/similar")
    suspend fun getSimilarSeries(
        @Path("series_id") seriesId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SimilarSeries>

    @GET("tv/{series_id}/recommendations")
    suspend fun getRecommendationSeries(
        @Path("series_id") seriesId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<RecommendationSeries>

    @GET("tv/{series_id}/images")
    suspend fun getSeriesImageList(
        @Path("series_id") seriesId: String
    ): Response<Image>

    @GET("tv/{series_id}/videos")
    suspend fun getSeriesVideoList(
        @Path("series_id") seriesId: String
    ): Response<Video>

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("query") query: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SeriesList>

    @GET("tv/{series_id}/reviews")
    suspend fun getSeriesReviewList(
        @Path("series_id") seriesId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<Review>

    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getSeasonDetail(
        @Path("series_id") seriesId: String,
        @Path("season_number") seasonNumber: Int
    ): Response<SerieSeasonDTO>

    @GET("tv/{series_id}/season/{season_number}/episode/{episode_number}")
    suspend fun getEpisodeDetail(
        @Path("series_id") seriesId: String,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Response<SerieEpisodeDTO>

    @GET("discover/tv")
    suspend fun discoverSeries(
        @Query("air_date.gte") airDateGte: String? = null,
        @Query("air_date.lte") airDateLte: String? = null,
        @Query("first_air_date_year") firstAirDateYear: Int? = null,
        @Query("first_air_date.gte") firstAirDateGte: String? = null,
        @Query("first_air_date.lte") firstAirDateLte: String? = null,
        @Query("include_adult") includeAdult: Boolean? = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean? = false,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1,
        @Query("screened_theatrically") screenedTheatrically: Boolean? = null,
        @Query("sort_by") sortBy: String? = "popularity.desc",
        @Query("timezone") timezone: String? = null,
        @Query("vote_average.gte") voteAverageGte: Float? = null,
        @Query("vote_average.lte") voteAverageLte: Float? = null,
        @Query("vote_count.gte") voteCountGte: Float? = null,
        @Query("vote_count.lte") voteCountLte: Float? = null,
        @Query("watch_region") watchRegion: String? = null,
        @Query("with_companies") withCompanies: String? = null,
        @Query("with_genres") withGenres: String? = null,
        @Query("with_keywords") withKeywords: String? = null,
        @Query("with_networks") withNetworks: Int? = null,
        @Query("with_origin_country") withOriginCountry: String? = null,
        @Query("with_original_language") withOriginalLanguage: String? = null,
        @Query("with_runtime.gte") withRuntimeGte: Int? = null,
        @Query("with_runtime.lte") withRuntimeLte: Int? = null,
        @Query("with_status") withStatus: String? = null,
        @Query("with_watch_monetization_types") withWatchMonetizationTypes: String? = null,
        @Query("with_watch_providers") withWatchProviders: String? = null,
        @Query("without_companies") withoutCompanies: String? = null,
        @Query("without_genres") withoutGenres: String? = null,
        @Query("without_keywords") withoutKeywords: String? = null,
        @Query("without_watch_providers") withoutWatchProviders: String? = null,
        @Query("with_type") withType: String? = null
    ): Response<SeriesList>
}