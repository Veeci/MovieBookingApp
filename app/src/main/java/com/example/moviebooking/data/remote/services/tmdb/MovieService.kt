package com.example.moviebooking.data.remote.services.tmdb

import com.example.moviebooking.data.remote.entities.tmdb.movie.Credit
import com.example.moviebooking.data.remote.entities.tmdb.movie.Genres
import com.example.moviebooking.data.remote.entities.tmdb.movie.Image
import com.example.moviebooking.data.remote.entities.tmdb.movie.Keyword
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieSearchResult
import com.example.moviebooking.data.remote.entities.tmdb.movie.RecommendationMovie
import com.example.moviebooking.data.remote.entities.tmdb.movie.Review
import com.example.moviebooking.data.remote.entities.tmdb.movie.SimilarMovie
import com.example.moviebooking.data.remote.entities.tmdb.movie.Video
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
    suspend fun getGenreList(
        @Query("language") language: String? = "en"
    ): Response<Genres>

    @GET("movie/{movie_id}/credits")
    suspend fun getCastList(
        @Path("movie_id") movieId: String
    ): Response<Credit>

    @GET("movie/{movie_id}/keywords")
    suspend fun getKeywordList(
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
    suspend fun getImageList(
        @Path("movie_id") movieId: String
    ): Response<Image>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoList(
        @Path("movie_id") movieId: String
    ): Response<Video>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<MovieSearchResult>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewList(
        @Path("movie_id") movieId: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<Review>
}