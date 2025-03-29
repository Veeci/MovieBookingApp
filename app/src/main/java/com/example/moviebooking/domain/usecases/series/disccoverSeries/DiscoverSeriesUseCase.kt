package com.example.moviebooking.domain.usecases.series.disccoverSeries

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import kotlinx.coroutines.flow.Flow

interface DiscoverSeriesUseCase {
    fun execute(
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
        withType: String? = null
        ): Flow<ResponseStatus<SeriesList>>
}