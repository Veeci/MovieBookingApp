package com.example.moviebooking.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.moviebooking.data.remote.entities.tmdb.Credit
import com.example.moviebooking.data.remote.entities.tmdb.Genres
import com.example.moviebooking.data.remote.entities.tmdb.Image
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import com.example.moviebooking.data.remote.entities.tmdb.Review
import com.example.moviebooking.data.remote.entities.tmdb.Video
import com.example.moviebooking.data.remote.entities.tmdb.series.EpisodesItem
import com.example.moviebooking.data.remote.entities.tmdb.series.RecommendationSeries
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieEpisodeDTO
import com.example.moviebooking.data.remote.entities.tmdb.series.SerieSeasonDTO
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import com.example.moviebooking.data.remote.entities.tmdb.series.SimilarSeries
import com.example.moviebooking.domain.usecases.series.airingTodayList.FetchAiringTodayUseCase
import com.example.moviebooking.domain.usecases.series.castList.FetchSeriesCastListUseCase
import com.example.moviebooking.domain.usecases.series.disccoverSeries.DiscoverSeriesUseCase
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
    private val fetchSeriesCastListUseCase: FetchSeriesCastListUseCase,
    private val discoverSeriesUseCase: DiscoverSeriesUseCase
) : BaseViewModel() {
    private val _airingTodayList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val airingTodayList: LiveData<ResponseStatus<SeriesList>> = _airingTodayList

    private val _isLoadingAiringToday: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingAiringToday: LiveData<Boolean> = _isLoadingAiringToday

    private val _onTheAirList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val onTheAirList: LiveData<ResponseStatus<SeriesList>> = _onTheAirList

    private val _isLoadingOnTheAir: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingOnTheAir: LiveData<Boolean> = _isLoadingOnTheAir

    private val _topRatedList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val topRatedList: LiveData<ResponseStatus<SeriesList>> = _topRatedList

    private val _isLoadingTopRated: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingTopRated: LiveData<Boolean> = _isLoadingTopRated

    private val _popularList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val popularList: LiveData<ResponseStatus<SeriesList>> = _popularList

    private val _isLoadingPopular: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingPopular: LiveData<Boolean> = _isLoadingPopular

    private val _fetchAllState: MutableLiveData<ResponseStatus<Unit>> = MutableLiveData()
    val fetchAllState: LiveData<ResponseStatus<Unit>> = _fetchAllState

    private val _seriesDetail: MutableLiveData<ResponseStatus<Series>> = MutableLiveData()
    val seriesDetail: LiveData<ResponseStatus<Series>> = _seriesDetail

    val currentSeries: MutableLiveData<Series> = MutableLiveData()

    private val _similarSeries: MutableLiveData<ResponseStatus<SimilarSeries>> = MutableLiveData()
    val similarSeries: LiveData<ResponseStatus<SimilarSeries>> = _similarSeries

    private val _recommendedSeries: MutableLiveData<ResponseStatus<RecommendationSeries>> =
        MutableLiveData()
    val recommendedSeries: LiveData<ResponseStatus<RecommendationSeries>> = _recommendedSeries

    private val _seriesSeasonDetail: MutableLiveData<ResponseStatus<SerieSeasonDTO>> =
        MutableLiveData()
    val seriesSeasonDetail: LiveData<ResponseStatus<SerieSeasonDTO>> = _seriesSeasonDetail

    private val _seriesEpisodeDetail: MutableLiveData<ResponseStatus<SerieEpisodeDTO>> =
        MutableLiveData()
    val seriesEpisodeDetail: LiveData<ResponseStatus<SerieEpisodeDTO>> = _seriesEpisodeDetail

    private val _seriesGenreList: MutableLiveData<ResponseStatus<Genres>> = MutableLiveData()
    val seriesGenreList: LiveData<ResponseStatus<Genres>> = _seriesGenreList

    private val _seriesReviews: MutableLiveData<ResponseStatus<Review>> = MutableLiveData()
    val seriesReviews: LiveData<ResponseStatus<Review>> = _seriesReviews

    private val _searchSeries: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val searchSeries: LiveData<ResponseStatus<SeriesList>> = _searchSeries

    private val _seriesImages: MutableLiveData<ResponseStatus<Image>> = MutableLiveData()
    val seriesImages: LiveData<ResponseStatus<Image>> = _seriesImages

    private val _seriesVideo: MutableLiveData<ResponseStatus<Video>> = MutableLiveData()
    val seriesVideo: LiveData<ResponseStatus<Video>> = _seriesVideo

    private val _seriesKeyword: MutableLiveData<ResponseStatus<Keyword>> = MutableLiveData()
    val seriesKeyword: LiveData<ResponseStatus<Keyword>> = _seriesKeyword

    private val _seriesCastList: MutableLiveData<ResponseStatus<Credit>> = MutableLiveData()
    val seriesCastList: LiveData<ResponseStatus<Credit>> = _seriesCastList

    private val _discoverList: MutableLiveData<ResponseStatus<SeriesList>> = MutableLiveData()
    val discoverList: LiveData<ResponseStatus<SeriesList>> = _discoverList

    val seasonEpisodeMap = MutableLiveData<Map<String, List<EpisodesItem>>>()
    private val tempSeasonEpisodeMap = mutableMapOf<String, List<EpisodesItem>>()

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

    fun fetchGenreList() {
        launchCoroutine {
            fetchSeriesGenreListUseCase.execute().collect { response ->
                _seriesGenreList.postValue(response)
            }
        }
    }

    fun fetchAiringTodayList(pageIndex: Int) {
        if (_isLoadingAiringToday.value == true) return

        launchCoroutine {
            _isLoadingAiringToday.postValue(true)

            try {
                fetchAiringTodayUseCase.fetchData(pageIndex).collect { response ->
                    if (response is ResponseStatus.Success) {
                        val currentList =
                            (_airingTodayList.value as? ResponseStatus.Success<SeriesList>)?.data?.results
                                ?: emptyList()

                        _airingTodayList.postValue(
                            ResponseStatus.Success(
                                response.data.copy(
                                    results = currentList + (response.data.results?.toList()
                                        ?: emptyList())
                                )
                            )
                        )
                    } else {
                        _airingTodayList.postValue(response)
                    }
                }
            } catch (e: Exception) {
                _airingTodayList.postValue(
                    ResponseStatus.Error(
                        e.message ?: "Unknown error occurred"
                    )
                )
            } finally {
                _isLoadingAiringToday.postValue(false)
            }
        }
    }

    fun fetchOnTheAirList(pageIndex: Int) {
        if (_isLoadingOnTheAir.value == true) return

        launchCoroutine {
            _isLoadingOnTheAir.postValue(true)

            try {
                fetchOnTheAirUseCase.execute(pageIndex).collect { response ->
                    _onTheAirList.postValue(response)
                }
            } catch (e: Exception) {
                _onTheAirList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingOnTheAir.postValue(false)
            }
        }
    }

    fun fetchTopRatedList(pageIndex: Int) {
        if (_isLoadingTopRated.value == true) return

        launchCoroutine {
            _isLoadingTopRated.postValue(true)

            try {
                fetchTopRatedUseCase.execute(pageIndex).collect { response ->
                    _topRatedList.postValue(response)
                }
            } catch (e: Exception) {
                _topRatedList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingTopRated.postValue(false)
            }
        }
    }

    fun fetchPopularList(pageIndex: Int) {
        if (_isLoadingPopular.value == true) return

        launchCoroutine {
            _isLoadingPopular.postValue(true)

            try {
                fetchPopularListUseCase.execute(pageIndex).collect { response ->
                    _popularList.postValue(response)
                }
            } catch (e: Exception) {
                _popularList.postValue(ResponseStatus.Error(e.message ?: "Unknown error occurred"))
            } finally {
                _isLoadingPopular.postValue(false)
            }
        }
    }

    fun fetchSeriesDetail(serieId: String) {
        launchCoroutine {
            fetchSeriesDetailUseCase.execute(serieId).collect { response ->
                _seriesDetail.postValue(response)
                if (response is ResponseStatus.Success) {
                    currentSeries.postValue(response.data)
                }
            }
        }
    }

    fun fetchSimilarSeries(serieId: String) {
        launchCoroutine {
            fetchSimilarSeriesUseCase.execute(serieId).collect { response ->
                _similarSeries.postValue(response)
            }
        }
    }

    fun fetchRecommendationSeries(serieId: String) {
        launchCoroutine {
            fetchRecommendationSeriesUseCase.execute(serieId).collect { response ->
                _recommendedSeries.postValue(response)
            }
        }
    }

    fun fetchSeriesCastList(serieId: String) {
        launchCoroutine {
            fetchSeriesCastListUseCase.execute(serieId).collect { response ->
                _seriesCastList.postValue(response)
            }
        }
    }

    fun fetchSeriesReviews(serieId: String) {
        launchCoroutine {
            fetchSeriesReviewsUseCase.execute(serieId).collect { response ->
                _seriesReviews.postValue(response)
            }
        }
    }

    fun fetchSeriesImages(serieId: String) {
        launchCoroutine {
            fetchSeriesImagesUseCase.execute(serieId).collect { response ->
                _seriesImages.postValue(response)
            }
        }
    }

    fun fetchSeriesVideo(serieId: String) {
        launchCoroutine {
            fetchSeriesVideoUseCase.execute(serieId).collect { response ->
                _seriesVideo.postValue(response)
            }
        }
    }

    fun fetchSeriesKeyword(serieId: String) {
        launchCoroutine {
            fetchSeriesKeywordUseCase.execute(serieId).collect { response ->
                _seriesKeyword.postValue(response)
            }
        }
    }

    fun searchSeries(query: String) {
        launchCoroutine {
            searchSeriesUseCase.execute(query).collect { response ->
                _searchSeries.postValue(response)
            }
        }
    }

    fun fetchSeriesSeasonDetail(serieId: String, seasonNumber: String) {
        launchCoroutine {
            fetchSeriesSeasonDetailUseCase.execute(serieId, seasonNumber.toInt())
                .collect { response ->
                    _seriesSeasonDetail.postValue(response)
                    if (response is ResponseStatus.Success) {
                        val seasonNum = response.data.seasonNumber.toString()
                        tempSeasonEpisodeMap[seasonNum] = response.data.episodes ?: emptyList()
                        seasonEpisodeMap.postValue(tempSeasonEpisodeMap.toMap())
                    }
                }
        }
    }

    fun fetchSeriesEpisodeDetail(serieId: String, seasonNumber: Int, episodeNumber: Int) {
        launchCoroutine {
            fetchSeriesEpisodeDetailUseCase.execute(serieId, seasonNumber, episodeNumber)
                .collect { response ->
                    _seriesEpisodeDetail.postValue(response)
                }
        }
    }

    fun discoverSeries(
        airDateGte: String? = null,
        airDateLte: String? = null,
        firstAirDateYear: Int? = null,
        firstAirDateGte: String? = null,
        firstAirDateLte: String? = null,
        includeAdult: Boolean? = false,
        includeNullFirstAirDates: Boolean? = false,
        language: String? = "en-US",
        page: Int? = 1,
        screenedTheatrically: Boolean? = null,
        sortBy: String? = "popularity.desc",
        timezone: String? = null,
        voteAverageGte: Float? = null,
        voteAverageLte: Float? = null,
        voteCountGte: Float? = null,
        voteCountLte: Float? = null,
        watchRegion: String? = null,
        withCompanies: String? = null,
        withGenres: String? = null,
        withKeywords: String? = null,
        withNetworks: Int? = null,
        withOriginCountry: String? = null,
        withOriginalLanguage: String? = null,
        withRuntimeGte: Int? = null,
        withRuntimeLte: Int? = null,
        withStatus: String? = null,
        withWatchMonetizationTypes: String? = null,
        withWatchProviders: String? = null,
        withoutCompanies: String? = null,
        withoutGenres: String? = null,
        withoutKeywords: String? = null,
        withoutWatchProviders: String? = null,
        withType: String? = null,
    ) {
        launchCoroutine {
            discoverSeriesUseCase.execute(
                airDateGte = airDateGte,
                airDateLte = airDateLte,
                firstAirDateYear = firstAirDateYear,
                firstAirDateGte = firstAirDateGte,
                firstAirDateLte = firstAirDateLte,
                includeAdult = includeAdult,
                includeNullFirstAirDates = includeNullFirstAirDates,
                language = language,
                page = page,
                screenedTheatrically = screenedTheatrically,
                sortBy = sortBy,
                timezone = timezone,
                voteAverageGte,
                voteAverageLte,
                voteCountGte,
                voteCountLte,
                watchRegion,
                withCompanies,
                withGenres,
                withKeywords,
                withNetworks,
                withOriginCountry,
                withOriginalLanguage,
                withRuntimeGte,
                withRuntimeLte,
                withStatus,
                withWatchMonetizationTypes,
                withWatchProviders,
                withoutCompanies,
                withoutGenres,
                withoutKeywords,
                withoutWatchProviders,
                withType
            ).collect { response ->
                _discoverList.postValue(response)
            }
        }
    }
}