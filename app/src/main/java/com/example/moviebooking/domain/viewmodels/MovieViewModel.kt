package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
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
) : BaseViewModel() {
    private val _nowPLayingList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val nowPLayingList: LiveData<ResponseStatus<MovieList>> = _nowPLayingList

    private val _localNowPlayingList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localNowPlayingList: LiveData<List<MovieItemEntity>> = _localNowPlayingList

    private val _popularList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val popularList: LiveData<ResponseStatus<MovieList>> = _popularList

    private val _localPopularList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localPopularList: LiveData<List<MovieItemEntity>> = _localPopularList

    private val _upcomingList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val upcomingList: LiveData<ResponseStatus<MovieList>> = _upcomingList

    private val _localUpcomingList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localUpcomingList: LiveData<List<MovieItemEntity>> = _localUpcomingList

    private val _topRatedList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val topRatedList: LiveData<ResponseStatus<MovieList>> = _topRatedList

    private val _localTopRatedList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localTopRatedList: LiveData<List<MovieItemEntity>> = _localTopRatedList

    private val _allMoviesFetchState: MutableLiveData<ResponseStatus<Unit>> = MutableLiveData()
    val allMoviesFetchState: LiveData<ResponseStatus<Unit>> = _allMoviesFetchState

    fun fetchAllMovies() {
        launchCoroutine {
            _allMoviesFetchState.postValue(ResponseStatus.Loading)

            try {
                val nowPlayingResult = async { fetchNowPLayingMovies.fetchData() }
                val popularResult = async { fetchPopularMovies.fetchData() }
                val upcomingResult = async { fetchUpcomingMovies.fetchData() }
                val topRatedResult = async { fetchTopRatedMovies.fetchData() }

                val nowPlayingResponse = nowPlayingResult.await()
                val popularResponse = popularResult.await()
                val upcomingResponse = upcomingResult.await()
                val topRatedResponse = topRatedResult.await()

                nowPlayingResponse.collect { response ->
                    _nowPLayingList.postValue(response)
                    if (response is ResponseStatus.Success) {
                        fetchNowPLayingMovies.saveData(response.data.toMovieItemEntities())
                    }
                }

                popularResponse.collect { response ->
                    _popularList.postValue(response)
                    if (response is ResponseStatus.Success) {
                        fetchPopularMovies.saveData(response.data.toMovieItemEntities())
                    }
                }

                upcomingResponse.collect { response ->
                    _upcomingList.postValue(response)
                    if (response is ResponseStatus.Success) {
                        fetchUpcomingMovies.saveData(response.data.toMovieItemEntities())
                    }
                }

                topRatedResponse.collect { response ->
                    _topRatedList.postValue(response)
                    if (response is ResponseStatus.Success) {
                        fetchTopRatedMovies.saveData(response.data.toMovieItemEntities())
                    }
                }

                _allMoviesFetchState.postValue(ResponseStatus.Success(Unit))

            } catch (e: Exception) {
                _allMoviesFetchState.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            }
        }
    }

    fun getLocalData() {
        launchCoroutine {
            fetchNowPLayingMovies.getFromLocal().collect {
                _localNowPlayingList.postValue(it)
            }
            fetchPopularMovies.getFromLocal().collect {
                _localPopularList.postValue(it)
            }
            fetchUpcomingMovies.getFromLocal().collect {
                _localUpcomingList.postValue(it)
                }
            fetchTopRatedMovies.getFromLocal().collect {
                _localTopRatedList.postValue(it)
            }
        }
    }
}