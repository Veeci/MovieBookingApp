package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
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

    private val _isLoadingNowPLaying: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingNowPLaying: LiveData<Boolean> = _isLoadingNowPLaying


    private val _popularList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val popularList: LiveData<ResponseStatus<MovieList>> = _popularList

    private val _localPopularList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localPopularList: LiveData<List<MovieItemEntity>> = _localPopularList

    private val _isLoadingPopular: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingPopular: LiveData<Boolean> = _isLoadingPopular


    private val _upcomingList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val upcomingList: LiveData<ResponseStatus<MovieList>> = _upcomingList

    private val _localUpcomingList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localUpcomingList: LiveData<List<MovieItemEntity>> = _localUpcomingList

    private val _isLoadingUpcoming: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingUpcoming: LiveData<Boolean> = _isLoadingUpcoming


    private val _topRatedList: MutableLiveData<ResponseStatus<MovieList>> = MutableLiveData()
    val topRatedList: LiveData<ResponseStatus<MovieList>> = _topRatedList

    private val _localTopRatedList: MutableLiveData<List<MovieItemEntity>> = MutableLiveData()
    val localTopRatedList: LiveData<List<MovieItemEntity>> = _localTopRatedList

    private val _isLoadingTopRated: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingTopRated: LiveData<Boolean> = _isLoadingTopRated


    private val _allMoviesFetchState: MutableLiveData<ResponseStatus<Unit>> = MutableLiveData()
    val allMoviesFetchState: LiveData<ResponseStatus<Unit>> = _allMoviesFetchState

    fun fetchAllMovies() {
        launchCoroutine {
            _allMoviesFetchState.postValue(ResponseStatus.Loading)

            try {
                val nowPlayingResult = async { fetchNowPLayingMovies.fetchData(1) }
                val popularResult = async { fetchPopularMovies.fetchData(1) }
                val upcomingResult = async { fetchUpcomingMovies.fetchData(1) }
                val topRatedResult = async { fetchTopRatedMovies.fetchData(1) }

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
                _allMoviesFetchState.postValue(
                    ResponseStatus.Error(
                        e.message ?: "Unknown error occurred"
                    )
                )
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

    fun fetchNowPlayingMovies(pageIndex: Int) {
        if (_isLoadingNowPLaying.value == true) return

        launchCoroutine {
            _isLoadingNowPLaying.postValue(true)

            try {
                fetchNowPLayingMovies.fetchData(pageIndex).collect { response ->
                    if (response is ResponseStatus.Success) {
                        val currentList =
                            (_nowPLayingList.value as? ResponseStatus.Success<MovieList>)?.data?.results
                                ?: emptyList()

                        _nowPLayingList.postValue(
                            ResponseStatus.Success(
                                response.data.copy(
                                    results = currentList + (response.data.results?.toList()
                                        ?: emptyList())
                                )
                            )
                        )

                        fetchNowPLayingMovies.saveData(response.data.toMovieItemEntities())
                    } else {
                        _nowPLayingList.postValue(response)
                    }

                }
            } catch(e: Exception) {
                _nowPLayingList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingNowPLaying.postValue(false)
            }
        }
    }

    fun fetchPopularMovies(pageIndex: Int) {
        if (_isLoadingPopular.value == true) return

        launchCoroutine {
            _isLoadingPopular.postValue(true)

            try {
                fetchPopularMovies.fetchData(pageIndex).collect { response ->
                    if (response is ResponseStatus.Success) {
                        val currentList =
                            (_popularList.value as? ResponseStatus.Success<MovieList>)?.data?.results
                                ?: emptyList()

                        _popularList.postValue(
                            ResponseStatus.Success(
                                response.data.copy(
                                    results = currentList + (response.data.results?.toList()
                                        ?: emptyList())
                                )
                            )
                        )
                        fetchPopularMovies.saveData(response.data.toMovieItemEntities())
                    } else {
                        _popularList.postValue(response)
                    }
                }
            } catch (e: Exception) {
                _popularList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingPopular.postValue(false)
            }
        }
    }

    fun fetchUpcomingMovies(pageIndex: Int) {
        if (_isLoadingUpcoming.value == true) return

        launchCoroutine {
            _isLoadingUpcoming.postValue(true)

            try {
                fetchUpcomingMovies.fetchData(pageIndex).collect { response ->
                    if (response is ResponseStatus.Success) {
                        val currentList =
                            (_upcomingList.value as? ResponseStatus.Success<MovieList>)?.data?.results
                                ?: emptyList()

                        _upcomingList.postValue(
                            ResponseStatus.Success(
                                response.data.copy(
                                    results = currentList + (response.data.results?.toList()
                                        ?: emptyList())
                                )
                            )
                        )
                        fetchUpcomingMovies.saveData(response.data.toMovieItemEntities())
                    } else {
                        _upcomingList.postValue(response)
                    }
                }
            } catch (e: Exception) {
                _upcomingList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingUpcoming.postValue(false)
            }
        }
    }

    fun fetchTopRatedMovies(pageIndex: Int) {
        if (_isLoadingTopRated.value == true) return

        launchCoroutine {
            _isLoadingTopRated.postValue(true)

            try {
                fetchTopRatedMovies.fetchData(pageIndex).collect { response ->
                    if (response is ResponseStatus.Success) {
                        val currentList =
                            (_topRatedList.value as? ResponseStatus.Success<MovieList>)?.data?.results
                                ?: emptyList()

                        _topRatedList.postValue(
                            ResponseStatus.Success(
                                response.data.copy(
                                    results = currentList + (response.data.results?.toList()
                                        ?: emptyList())
                                )
                            )
                        )
                        fetchTopRatedMovies.saveData(response.data.toMovieItemEntities())
                    } else {
                        _topRatedList.postValue(response)
                    }
                }
            } catch (e: Exception) {
                _topRatedList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingTopRated.postValue(false)
            }
        }
    }
}