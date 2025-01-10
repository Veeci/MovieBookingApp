package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.popularList.FetchPopularMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.topRatedList.FetchTopRatedMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.upcomingList.FetchUpcomingMoviesUseCase
import kotlinx.coroutines.async

class MovieViewModel(
    private val fetchNowPLayingMovies: FetchNowPlayingMoviesUseCase,
    private val fetchPopularMovies: FetchPopularMoviesUseCase,
    private val fetchUpcomingMovies: FetchUpcomingMoviesUseCase,
    private val fetchTopRatedMovies: FetchTopRatedMoviesUseCase
): BaseViewModel() {
    private val _nowPLayingList: MutableLiveData<ResponseStatus<MovieItem>> = MutableLiveData()
    val nowPLayingList: LiveData<ResponseStatus<MovieItem>> = _nowPLayingList

    private val _popularList: MutableLiveData<ResponseStatus<MovieItem>> = MutableLiveData()
    val popularList: LiveData<ResponseStatus<MovieItem>> = _popularList

    private val _upcomingList: MutableLiveData<ResponseStatus<MovieItem>> = MutableLiveData()
    val upcomingList: LiveData<ResponseStatus<MovieItem>> = _upcomingList

    private val _topRatedList: MutableLiveData<ResponseStatus<MovieItem>> = MutableLiveData()
    val topRatedList: LiveData<ResponseStatus<MovieItem>> = _topRatedList

    private val _allMoviesFetchState: MutableLiveData<ResponseStatus<Unit>> = MutableLiveData()
    val allMoviesFetchState: LiveData<ResponseStatus<Unit>> = _allMoviesFetchState

    fun getNowPlayingMovies() {
        launchCoroutine {
            fetchNowPLayingMovies.execute().collect {
                _nowPLayingList.postValue(it)
            }
        }
    }

    fun getPopularMovies() {
        launchCoroutine {
            fetchPopularMovies.execute().collect{
                _popularList.postValue(it)
            }
        }
    }

    fun getUpcomingMovies() {
        launchCoroutine {
            fetchUpcomingMovies.execute().collect{
                _upcomingList.postValue(it)
            }
        }
    }

    fun getTopRatedMovies() {
        launchCoroutine {
            fetchTopRatedMovies.execute().collect{
                _topRatedList.postValue(it)
            }
        }
    }

    fun fetchAllMovies() {
        launchCoroutine {
            _allMoviesFetchState.postValue(ResponseStatus.Loading)

            try {
                val nowPlayingDeferred = async { fetchUpcomingMovies.execute() }
                val popularDeferred = async { fetchPopularMovies.execute() }
                val upcomingDeferred = async { fetchUpcomingMovies.execute() }
                val topRatedDeferred = async { fetchTopRatedMovies.execute() }

                val nowPlayingResult = nowPlayingDeferred.await()
                val popularResult = popularDeferred.await()
                val upcomingResult = upcomingDeferred.await()
                val topRatedResult = topRatedDeferred.await()

                nowPlayingResult.collect{
                    _nowPLayingList.postValue(it)
                }

                popularResult.collect{
                    _popularList.postValue(it)
                }

                upcomingResult.collect{
                    _upcomingList.postValue(it)
                }

                topRatedResult.collect{
                    _topRatedList.postValue(it)
                }

                _allMoviesFetchState.postValue(ResponseStatus.Success(Unit))
            } catch (e: Exception) {
                _nowPLayingList.postValue(ResponseStatus.Error(e.message ?: "Error fetching now playing movies"))
                _popularList.postValue(ResponseStatus.Error(e.message ?: "Error fetching popular movies"))
                _upcomingList.postValue(ResponseStatus.Error(e.message ?: "Error fetching upcoming movies"))
                _topRatedList.postValue(ResponseStatus.Error(e.message ?: "Error fetching top-rated movies"))

                _allMoviesFetchState.postValue(ResponseStatus.Error(e.message ?: "Error fetching all movies"))

            }
        }
    }
}