package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import com.example.moviebooking.domain.usecases.series.airingTodayList.FetchAiringTodayUseCase
import com.example.moviebooking.domain.usecases.series.castList.FetchSeriesCastListUseCase
import com.example.moviebooking.domain.usecases.series.episodeDetail.FetchSeriesEpisodeDetailUseCase
import com.example.moviebooking.domain.usecases.series.genreList.FetchSeriesGenreListUseCase
import com.example.moviebooking.domain.usecases.series.images.FetchSeriesImagesUseCase
import com.example.moviebooking.domain.usecases.series.keywordList.FetchSeriesKeywordUseCase
import com.example.moviebooking.domain.usecases.series.onTheAirList.FetchOnTheAirUseCase
import com.example.moviebooking.domain.usecases.series.popularList.FetchPopularListUseCase
import com.example.moviebooking.domain.usecases.series.recommendedList.FetchRecommendationSeriesUseCase
import com.example.moviebooking.domain.usecases.series.reviews.FetchSeriesReviewsUseCase
import com.example.moviebooking.domain.usecases.series.search.SearchSeriesUseCase
import com.example.moviebooking.domain.usecases.series.seasonDetail.FetchSeriesSeasonDetailUseCase
import com.example.moviebooking.domain.usecases.series.seriesDetail.FetchSeriesDetailUseCase
import com.example.moviebooking.domain.usecases.series.similarList.FetchSimilarSeriesUseCase
import com.example.moviebooking.domain.usecases.series.topRatedList.FetchTopRatedUseCase
import com.example.moviebooking.domain.usecases.series.videos.FetchSeriesVideoUseCase
import kotlinx.coroutines.async

class SeriesViewModel(
    private val fetchAiringTodayUseCase: FetchAiringTodayUseCase,
    private val fetchOnTheAirUseCase: FetchOnTheAirUseCase,
    private val fetchTopRatedUseCase: FetchTopRatedUseCase,
    private val fetchPopularListUseCase: FetchPopularListUseCase,
    private val fetchSeriesDetailUseCase: FetchSeriesDetailUseCase,
    private val fetchSimilarSeriesUseCase: FetchSimilarSeriesUseCase,
    private val fetchRecommendationSeriesUseCase: FetchRecommendationSeriesUseCase,
    private val fetchSeriesSeasonDetailUseCase: FetchSeriesSeasonDetailUseCase,
    private val fetchSeriesEpisodeDetailUseCase: FetchSeriesEpisodeDetailUseCase,
    private val fetchSeriesGenreListUseCase: FetchSeriesGenreListUseCase,
    private val fetchSeriesReviewsUseCase: FetchSeriesReviewsUseCase,
    private val searchSeriesUseCase: SearchSeriesUseCase,
    private val fetchSeriesImagesUseCase: FetchSeriesImagesUseCase,
    private val fetchSeriesVideoUseCase: FetchSeriesVideoUseCase,
    private val fetchSeriesKeywordUseCase: FetchSeriesKeywordUseCase,
    private val fetchSeriesCastListUseCase: FetchSeriesCastListUseCase
) : BaseViewModel() {
    private val _airingTodayList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val airingTodayList: LiveData<ResponseStatus<SeriesList>> = _airingTodayList

    private val _onTheAirList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val onTheAirList: LiveData<ResponseStatus<SeriesList>> = _onTheAirList

    private val _topRatedList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val topRatedList: LiveData<ResponseStatus<SeriesList>> = _topRatedList

    private val _popularList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val popularList: LiveData<ResponseStatus<SeriesList>> = _popularList

    private val _fetchAllState: MutableLiveData<ResponseStatus<Unit>> = MutableLiveData()
    val fetchAllState: LiveData<ResponseStatus<Unit>> = _fetchAllState

    private val _seriesDetail: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesDetail: LiveData<ResponseStatus<SeriesList>> = _seriesDetail

    private val _similarSeries: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val similarSeries: LiveData<ResponseStatus<SeriesList>> = _similarSeries

    private val _recommendedSeries: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val recommendedSeries: LiveData<ResponseStatus<SeriesList>> = _recommendedSeries

    private val _seriesSeasonDetail: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesSeasonDetail: LiveData<ResponseStatus<SeriesList>> = _seriesSeasonDetail

    private val _seriesEpisodeDetail: MutableLiveData<ResponseStatus<SeriesList>> =
        MutableLiveData()
    val seriesEpisodeDetail: LiveData<ResponseStatus<SeriesList>> = _seriesEpisodeDetail

    private val _seriesGenreList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesGenreList: LiveData<ResponseStatus<SeriesList>> = _seriesGenreList

    private val _seriesReviews: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesReviews: LiveData<ResponseStatus<SeriesList>> = _seriesReviews

    private val _searchSeries: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val searchSeries: LiveData<ResponseStatus<SeriesList>> = _searchSeries

    private val _seriesImages: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesImages: LiveData<ResponseStatus<SeriesList>> = _seriesImages

    private val _seriesVideo: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesVideo: LiveData<ResponseStatus<SeriesList>> = _seriesVideo

    private val _seriesKeyword: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesKeyword: LiveData<ResponseStatus<SeriesList>> = _seriesKeyword

    private val _seriesCastList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val seriesCastList: LiveData<ResponseStatus<SeriesList>> = _seriesCastList

    fun fetchAllSeries() {
        launchCoroutine {
            _fetchAllState.postValue(ResponseStatus.Loading)

            try {
                val airingTodayResult = async { fetchAiringTodayUseCase.fetchData(1) }
                val onTheAirResult = async { fetchOnTheAirUseCase.execute(1) }
                val topRatedResult = async { fetchTopRatedUseCase.execute(1) }
                val popularResult = async { fetchPopularListUseCase.execute(1) }

                val airingTodayResponse = airingTodayResult.await()
                val onTheAirResponse = onTheAirResult.await()
                val topRatedResponse = topRatedResult.await()
                val popularResponse = popularResult.await()

                airingTodayResponse.collect { response ->
                    _airingTodayList.postValue(response)
                }

                onTheAirResponse.collect { response ->
                    _onTheAirList.postValue(response)
                }

                topRatedResponse.collect { response ->
                    _topRatedList.postValue(response)

                }

                popularResponse.collect { response ->
                    _popularList.postValue(response)
                }

                _fetchAllState.postValue(ResponseStatus.Success(Unit))
            } catch (e: Exception) {
                _fetchAllState.postValue(
                    ResponseStatus.Error(
                        e.message ?: "Unknown error occurred"
                    )
                )
            }
        }
    }
}