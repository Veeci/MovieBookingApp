package com.example.moviebooking.data.remote.services.tmdb

import com.example.moviebooking.data.remote.entities.tmdb.Image
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleDTO
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleItemDTO
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleSearchingResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<PeopleItemDTO>

    @GET("person/{person_id}")
    suspend fun getPersonDetail(
        @Query("person_id") personId: String
    ): Response<PeopleDTO>

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieCredits(
        @Query("person_id") personId: String
    ): Response<PeopleDTO>

    @GET("person/{person_id}/tv_credits")
    suspend fun getTvCredits(
        @Query("person_id") personId: String
    ): Response<PeopleDTO>

    @GET("search/person")
    suspend fun searchPeople(
        @Query("query") query: String,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int? = 1
    ): Response<PeopleSearchingResultDTO>

    @GET("person/{person_id}/images")
    suspend fun getPeopleImages(
        @Query("person_id") personId: String
    ): Response<Image>
}