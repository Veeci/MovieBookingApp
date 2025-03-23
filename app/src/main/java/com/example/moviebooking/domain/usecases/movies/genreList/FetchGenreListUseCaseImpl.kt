package com.example.moviebooking.domain.usecases.movies.genreList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.dao.MovieGenreDao
import com.example.moviebooking.data.local.entities.MovieGenreEntity
import com.example.moviebooking.data.remote.entities.tmdb.Genres
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchGenreListUseCaseImpl(
    private val apiService: TMDBService,
    private val movieGenreDao: MovieGenreDao
): FetchGenreListUseCase, ApiHandler {
    override fun fetchData(): Flow<ResponseStatus<Genres>> {
        return flow {
            emit(handleApi { apiService.getGenreList() })
        }.onStart {
            this.emit(ResponseStatus.Loading)
        }.catch {
            this.emit(
                ResponseStatus.Error(
                    message = it.message ?: "Something went wrong",
                    errorCode = 500
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun saveData(list: List<MovieGenreEntity>): Flow<Boolean> {
        return flow {
            for (item in list) {
                movieGenreDao.insert(item)
            }
            this.emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override fun getFromLocal(): Flow<List<MovieGenreEntity>> {
        return flow<List<MovieGenreEntity>> {
            movieGenreDao.getAll().takeIf { it.isNotEmpty() }?.let { items ->
                this.emit(items)
            } ?: run {
                this.emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }
}