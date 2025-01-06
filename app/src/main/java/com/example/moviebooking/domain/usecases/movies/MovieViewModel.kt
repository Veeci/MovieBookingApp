package com.example.moviebooking.domain.usecases.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem

class MovieViewModel(
    private val fetchNowPLayingMovies: FetchNowPlayingMoviesUseCase,
): BaseViewModel() {
    private val _nowPLayingList: MutableLiveData<ResponseStatus<MovieItem>> = MutableLiveData()
    val nowPLayingList: LiveData<ResponseStatus<MovieItem>> = _nowPLayingList

    fun getNowPlayingMovies() {
        launchCoroutine {
            fetchNowPLayingMovies.execute().collect {
                _nowPLayingList.postValue(it)
            }
        }
    }
}