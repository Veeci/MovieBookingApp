package com.example.moviebooking.domain.usecases.movies.popularList

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.dao.MovieItemDao
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchPopularMoviesUseCaseImpl(
    private val apiService: TMDBService,
    private val movieItemDao: MovieItemDao
): FetchPopularMoviesUseCase, ApiHandler {
    override fun fetchData(): Flow<ResponseStatus<MovieItem>> {
        return flow {
            emit(handleApi { apiService.getPopularMovies() })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch {
            emit(ResponseStatus.Error(
                message = it.message ?: "Unknown Error",
                errorCode = 500
            ))
        }.flowOn(Dispatchers.IO)
    }

    override fun saveData(list: List<MovieItemEntity>): Flow<Boolean> {
        return flow {
            for (item in list) {
                movieItemDao.insert(item)
            }
            this.emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override fun getFromLocal(): Flow<List<MovieItemEntity>> {
        return flow<List<MovieItemEntity>> {
            movieItemDao.getAll().takeIf { it.isNotEmpty() }?.let { items ->
                this.emit(items)
            } ?: run {
                this.emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }
}