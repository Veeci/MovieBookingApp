package com.example.moviebooking.data.remote.services.tmdb

import com.example.moviebooking.data.remote.entities.tmdb.Credit
import com.example.moviebooking.data.remote.entities.tmdb.Genres
import com.example.moviebooking.data.remote.entities.tmdb.Image
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieSearchResultDTO
import com.example.moviebooking.data.remote.entities.tmdb.movie.RecommendationMovie
import com.example.moviebooking.data.remote.entities.tmdb.Review
import com.example.moviebooking.data.remote.entities.tmdb.movie.SimilarMovie
import com.example.moviebooking.data.remote.entities.tmdb.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<MovieList>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<MovieList>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<MovieList>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String
    ): Response<Movie>

    @GET("genre/movie/list")
    suspend fun getMovieGenreList(
        @Query("language") language: String? = "en"
    ): Response<Genres>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCastList(
        @Path("movie_id") movieId: String
    ): Response<Credit>

    @GET("movie/{movie_id}/keywords")
    suspend fun getMovieKeywordList(
        @Path("movie_id") movieId: String
    ): Response<Keyword>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<SimilarMovie>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendationMovies(
        @Path("movie_id") movieId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<RecommendationMovie>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImageList(
        @Path("movie_id") movieId: String
    ): Response<Image>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideoList(
        @Path("movie_id") movieId: String
    ): Response<Video>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<MovieSearchResultDTO>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviewList(
        @Path("movie_id") movieId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<Review>

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("certification") certification: String? = null,
        @Query("certification.gte") certificationGte: String? = null,
        @Query("certification.lte") certificationLte: String? = null,
        @Query("certification_country") certificationCountry: String? = null,
        @Query("include_adult") includeAdult: Boolean? = false,
        @Query("include_video") includeVideo: Boolean? = false,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1,
        @Query("primary_release_year") primaryReleaseYear: Int? = null,
        @Query("primary_release_date.gte") primaryReleaseDateGte: String? = null,
        @Query("primary_release_date.lte") primaryReleaseDateLte: String? = null,
        @Query("region") region: String? = null,
        @Query("release_date.gte") releaseDateGte: String? = null,
        @Query("release_date.lte") releaseDateLte: String? = null,
        @Query("sort_by") sortBy: String? = "popularity.desc",
        @Query("vote_average.gte") voteAverageGte: Float? = null,
        @Query("vote_average.lte") voteAverageLte: Float? = null,
        @Query("vote_count.gte") voteCountGte: Float? = null,
        @Query("vote_count.lte") voteCountLte: Float? = null,
        @Query("with_cast") withCast: String? = null,
        @Query("with_companies") withCompanies: String? = null,
        @Query("with_crew") withCrew: String? = null,
        @Query("with_genres") withGenres: String? = null,
        @Query("with_keywords") withKeywords: String? = null,
        @Query("with_origin_country") withOriginCountry: String? = null,
        @Query("with_original_language") withOriginalLanguage: String? = null,
        @Query("with_people") withPeople: String? = null,
        @Query("with_release_type") withReleaseType: Int? = null,
        @Query("with_runtime.gte") withRuntimeGte: Int? = null,
        @Query("with_runtime.lte") withRuntimeLte: Int? = null,
        @Query("year") year: Int? = null
    ): Response<MovieList>
}