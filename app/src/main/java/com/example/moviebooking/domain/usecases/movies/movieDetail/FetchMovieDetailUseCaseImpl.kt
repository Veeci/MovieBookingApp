package com.example.moviebooking.domain.usecases.movies.movieDetail

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.dao.MovieDao
import com.example.moviebooking.data.local.entities.MovieEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchMovieDetailUseCaseImpl(
    private val apiService: TMDBService,
    private val movieDao: MovieDao
): FetchMovieDetailUseCase, ApiHandler {
    override fun fetchData(movieID: String): Flow<ResponseStatus<Movie>> {
        return flow {
            emit(handleApi { apiService.getMovieDetail(movieId = movieID) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(
                ResponseStatus.Error(
                    message = e.message ?: "Something went wrong",
                    errorCode = 500
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun saveData(movie: MovieEntity): Flow<Boolean> {
        return flow {
            movieDao.insert(movie)
            this.emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override fun getFromLocal(movieID: String): Flow<MovieEntity> {
        return flow {
            movieDao.getById(movieID)?.let { item ->
                this.emit(item)
            } ?: run {
                this.emit(MovieEntity(movieID))
            }
        }
    }
}