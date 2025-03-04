package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.data.remote.entities.tmdb.movie.Credit
import com.example.moviebooking.data.remote.entities.tmdb.movie.Genre
import com.example.moviebooking.data.remote.entities.tmdb.movie.Genres
import com.example.moviebooking.data.remote.entities.tmdb.movie.Image
import com.example.moviebooking.data.remote.entities.tmdb.movie.Keyword
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieSearchResult
import com.example.moviebooking.data.remote.entities.tmdb.movie.RecommendationMovie
import com.example.moviebooking.data.remote.entities.tmdb.movie.Review
import com.example.moviebooking.data.remote.entities.tmdb.movie.SimilarMovie
import com.example.moviebooking.data.remote.entities.tmdb.movie.Video
import com.example.moviebooking.domain.usecases.movies.castList.FetchCastListUseCase
import com.example.moviebooking.domain.usecases.movies.genreList.FetchGenreListUseCase
import com.example.moviebooking.domain.usecases.movies.getKeywords.FetchKeywordUseCase
import com.example.moviebooking.domain.usecases.movies.images.FetchImageUseCase
import com.example.moviebooking.domain.usecases.movies.movieDetail.FetchMovieDetailUseCase
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.popularList.FetchPopularMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.recommendedList.FetchRecommendedMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.reviews.FetchReviewsUseCase
import com.example.moviebooking.domain.usecases.movies.search.FetchMovieSearchingResultUseCase
import com.example.moviebooking.domain.usecases.movies.similarList.FetchSimilarMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.topRatedList.FetchTopRatedMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.upcomingList.FetchUpcomingMoviesUseCase
import com.example.moviebooking.domain.usecases.movies.videos.FetchVideoUseCase
import kotlinx.coroutines.async

class MovieViewModel(
    private val fetchNowPLayingMovies: FetchNowPlayingMoviesUseCase,
    private val fetchPopularMovies: FetchPopularMoviesUseCase,
    private val fetchUpcomingMovies: FetchUpcomingMoviesUseCase,
    private val fetchTopRatedMovies: FetchTopRatedMoviesUseCase,
    private val fetchGenreList: FetchGenreListUseCase,
    private val fetchMovieDetail: FetchMovieDetailUseCase,
    private val fetchCastList: FetchCastListUseCase,
    private val fetchKeywordList: FetchKeywordUseCase,
    private val fetchSimilarMovies: FetchSimilarMoviesUseCase,
    private val fetchRecommendedMovies: FetchRecommendedMoviesUseCase,
    private val fetchImage: FetchImageUseCase,
    private val fetchVideo: FetchVideoUseCase,
    private val fetchMovieSearchingResult: FetchMovieSearchingResultUseCase,
    private val fetchReviews: FetchReviewsUseCase
) : BaseViewModel() {
    var movieId: MutableLiveData<String> = MutableLiveData()

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

    private val _movieDetail: MutableLiveData<ResponseStatus<Movie>> = MutableLiveData()
    val movieDetail: LiveData<ResponseStatus<Movie>> = _movieDetail

    private val _genreList: MutableLiveData<ResponseStatus<Genres>> = MutableLiveData()
    val genreList: LiveData<ResponseStatus<Genres>> = _genreList

    private val _localGenreList: MutableLiveData<List<Genre>> = MutableLiveData()
    val localGenreList: LiveData<List<Genre>> = _localGenreList

    private val _castList: MutableLiveData<ResponseStatus<Credit>> = MutableLiveData()
    val castList: LiveData<ResponseStatus<Credit>> = _castList

    private val _keywordList: MutableLiveData<ResponseStatus<Keyword>> = MutableLiveData()
    val keywordList: LiveData<ResponseStatus<Keyword>> = _keywordList

    private val _similarMovies: MutableLiveData<ResponseStatus<SimilarMovie>> = MutableLiveData()
    val similarMovies: LiveData<ResponseStatus<SimilarMovie>> = _similarMovies

    private val _recommendedMovies: MutableLiveData<ResponseStatus<RecommendationMovie>> = MutableLiveData()
    val recommendedMovies: LiveData<ResponseStatus<RecommendationMovie>> = _recommendedMovies

    private val _imageList: MutableLiveData<ResponseStatus<Image>> = MutableLiveData()
    val imageList: LiveData<ResponseStatus<Image>> = _imageList

    private val _videoList: MutableLiveData<ResponseStatus<Video>> = MutableLiveData()
    val videoList: LiveData<ResponseStatus<Video>> = _videoList

    private val _movieSearchingResult: MutableLiveData<ResponseStatus<MovieSearchResult>> = MutableLiveData()
    val movieSearchingResult: LiveData<ResponseStatus<MovieSearchResult>> = _movieSearchingResult

    private val _reviews: MutableLiveData<ResponseStatus<Review>> = MutableLiveData()
    val reviews: LiveData<ResponseStatus<Review>> = _reviews

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
            } catch (e: Exception) {
                _nowPLayingList.postValue(
                    ResponseStatus.Error(
                        e.message ?: "Unknown error occurred"
                    )
                )
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

    fun fetchMovieDetail(movieID: String) {
        launchCoroutine {
            fetchMovieDetail.fetchData(movieID).collect { response ->
                _movieDetail.postValue(response)
                if (response is ResponseStatus.Success) {
                    fetchMovieDetail.saveData(response.data.toEntity())
                }
            }
        }
    }

    fun fetchGenreList() {
        launchCoroutine {
            fetchGenreList.fetchData().collect { response ->
                _genreList.postValue(response)
                if (response is ResponseStatus.Success) {
                    fetchGenreList.saveData(response.data.toGenreEntities())
                }
            }
        }
    }

    fun fetchCastList(movieID: String) {
        launchCoroutine {
            fetchCastList.fetchData(movieID).collect { response ->
                _castList.postValue(response)
            }
        }
    }

    fun fetchKeywordList(movieID: String) {
        launchCoroutine {
            fetchKeywordList.fetchData(movieID).collect { response ->
                _keywordList.postValue(response)
            }
        }
    }

    fun fetchSimilarMovies(movieID: String) {
        launchCoroutine {
            fetchSimilarMovies.fetchData(movieID).collect { response ->
                _similarMovies.postValue(response)
            }
        }
    }

    fun fetchRecommendedMovies(movieID: String) {
        launchCoroutine {
            fetchRecommendedMovies.fetchData(movieID).collect { response ->
                _recommendedMovies.postValue(response)
            }
        }
    }

    fun fetchImage(movieID: String) {
        launchCoroutine {
            fetchImage.fetchData(movieID).collect { response ->
                _imageList.postValue(response)
            }
        }
    }

    fun fetchVideo(movieID: String) {
        launchCoroutine {
            fetchVideo.fetchData(movieID).collect { response ->
                _videoList.postValue(response)
            }
        }
    }

    fun searchMovie(query: String) {
        launchCoroutine {
            fetchMovieSearchingResult.fetchData(query).collect { response ->
                _movieSearchingResult.postValue(response)
            }
        }
    }

    fun fetchReviews(movieID: String) {
        launchCoroutine {
            fetchReviews.fetchData(movieID).collect { response ->
                _reviews.postValue(response)
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