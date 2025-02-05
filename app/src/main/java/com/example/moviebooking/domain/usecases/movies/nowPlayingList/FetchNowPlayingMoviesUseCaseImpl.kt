package com.example.moviebooking.domain.usecases.movies.nowPlayingList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.local.dao.MovieItemDao
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchNowPlayingMoviesUseCaseImpl(
    private val apiService: TMDBService,
    private val movieItemDao: MovieItemDao
) : FetchNowPlayingMoviesUseCase {
    override fun execute(): Flow<ResponseStatus<MovieItem>> {
        return flow {
            with(apiService.getNowPlayingMovies()) {
                if (this.isSuccess()) {
                    this.data?.let { response ->
                        emit(ResponseStatus.Success(response))
                        movieItemDao.insertAll(response.toMovieItemEntities())
                        return@with
                    }
                }
                emit(
                    ResponseStatus.Error(
                        message = this.message ?: "Something went wrong",
                        errorCode = this.errorCode ?: 500
                    )
                )
            }
        }.onStart {
            this.emit(ResponseStatus.Loading)
        }.catch { e ->
            this.emit(
                ResponseStatus.Error(
                    message = e.message ?: "Something went wrong",
                    errorCode = 500
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getFromLocal(): Flow<ResponseStatus<MovieItem>> {
        return flow {
            val movieItems = movieItemDao.getAll()
            if (movieItems.isNotEmpty()) {

            }
        }
    }
}

