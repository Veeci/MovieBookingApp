package com.example.moviebooking.data.remote.services.tmdb

import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<MovieItem>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieItem>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MovieItem>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieItem>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Response<Movie>
}