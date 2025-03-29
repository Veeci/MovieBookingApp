package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.remote.entities.tmdb.Image
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleDTO
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleItemDTO
import com.example.moviebooking.data.remote.entities.tmdb.people.PeopleSearchingResultDTO
import com.example.moviebooking.domain.usecases.people.getMovieCredits.FetchMovieCreditsUseCase
import com.example.moviebooking.domain.usecases.people.getPersonDetail.FetchPersonDetailUseCase
import com.example.moviebooking.domain.usecases.people.getPersonImages.FetchPersonImagesUseCase
import com.example.moviebooking.domain.usecases.people.getPopularPeople.FetchPopularPeopleUseCase
import com.example.moviebooking.domain.usecases.people.getSeriesCredits.FetchSeriesCreditsUseCase
import com.example.moviebooking.domain.usecases.people.searchPeople.SearchPeopleUseCase

class PeopleViewModel(
    private val fetchPopularPeopleUseCase: FetchPopularPeopleUseCase,
    private val fetchPersonDetailUseCase: FetchPersonDetailUseCase,
    private val fetchPersonImagesUseCase: FetchPersonImagesUseCase,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase,
    private val fetchSeriesCreditsUseCase: FetchSeriesCreditsUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase
) : BaseViewModel() {
    private val _popularPeopleList: MutableLiveData<ResponseStatus<PeopleItemDTO>> = MutableLiveData()
    val popularPeopleList: LiveData<ResponseStatus<PeopleItemDTO>> = _popularPeopleList

    private val _isLoadingPeopleList: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingPeopleList: LiveData<Boolean> = _isLoadingPeopleList

    private val _personDetail: MutableLiveData<ResponseStatus<PeopleDTO>> = MutableLiveData()
    val personDetail: LiveData<ResponseStatus<PeopleDTO>> = _personDetail

    private val _personImages: MutableLiveData<ResponseStatus<Image>> = MutableLiveData()
    val personImages: LiveData<ResponseStatus<Image>> = _personImages

    private val _movieCredits: MutableLiveData<ResponseStatus<PeopleDTO>> = MutableLiveData()
    val movieCredits: LiveData<ResponseStatus<PeopleDTO>> = _movieCredits

    private val _seriesCredits: MutableLiveData<ResponseStatus<PeopleDTO>> = MutableLiveData()
    val seriesCredits: LiveData<ResponseStatus<PeopleDTO>> = _seriesCredits

    private val _searchPeople: MutableLiveData<ResponseStatus<PeopleSearchingResultDTO>> =
        MutableLiveData()
    val searchPeople: LiveData<ResponseStatus<PeopleSearchingResultDTO>> = _searchPeople

    fun fetchPeopleList(page: Int? = 1) {
        if(_isLoadingPeopleList.value == true) return

        launchCoroutine {
            try {
                fetchPopularPeopleUseCase.execute(page ?: 1).collect { response ->
                    if (response is ResponseStatus.Success) {
                        val currentList = (_popularPeopleList.value as? ResponseStatus.Success<PeopleItemDTO>)?.data?.results ?: emptyList()

                        _popularPeopleList.postValue(
                            ResponseStatus.Success(
                                response.data.copy(
                                    results = currentList + (response.data.results?.toList() ?: emptyList())
                                )
                            )
                        )
                    } else {
                        _popularPeopleList.postValue(response)
                    }
                }
            } catch (e: Exception) {
                _popularPeopleList.postValue(
                    ResponseStatus.Error(
                        e.message ?: "Unknown error occurred"
                    )
                )
            } finally {
                _isLoadingPeopleList.postValue(false)
            }
        }
    }

    fun fetchPersonDetail(personId: String) {
        launchCoroutine {
            fetchPersonDetailUseCase.execute(personId).collect { response ->
                _personDetail.postValue(response)
            }
        }
    }

    fun fetchPersonImages(personId: String) {
        launchCoroutine {
            fetchPersonImagesUseCase.execute(personId).collect { response ->
                _personImages.postValue(response)
            }
        }
    }

    fun fetchMovieCredits(personId: String) {
        launchCoroutine {
            fetchMovieCreditsUseCase.execute(personId).collect { response ->
                _movieCredits.postValue(response)
            }
        }
    }

    fun fetchSeriesCredits(personId: String) {
        launchCoroutine {
            fetchSeriesCreditsUseCase.execute(personId).collect { response ->
                _seriesCredits.postValue(response)
            }
        }
    }

    fun searchPeople(query: String, page: Int) {
        launchCoroutine {
            searchPeopleUseCase.execute(query, page).collect { response ->
                _searchPeople.postValue(response)
            }
        }
    }
}