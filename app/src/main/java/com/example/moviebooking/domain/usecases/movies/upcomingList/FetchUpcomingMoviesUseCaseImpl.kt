package com.example.moviebooking.domain.usecases.movies.upcomingList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class FetchUpcomingMoviesUseCaseImpl(
    val apiService: TMDBService
): FetchUpcomingMoviesUseCase {
    override fun execute(): Flow<ResponseStatus<MovieItem>> {
        return flow {
            with(apiService.getUpcomingMovies()) {
                if(this.isSuccess()) {
                    this.data?.let {
                        emit(ResponseStatus.Success(it))
                        return@with
                    }
                }
                emit(ResponseStatus.Error(
                    message = this.message ?: "Unknown Error",
                    errorCode = this.errorCode ?: 500
                ))
            }
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch {
            emit(ResponseStatus.Error(
                message = it.message ?: "Unknown Error",
                errorCode = 500
            ))
        }.flowOn(Dispatchers.IO)
    }
}