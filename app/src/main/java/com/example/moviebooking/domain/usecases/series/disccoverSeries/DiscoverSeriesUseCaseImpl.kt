package com.example.moviebooking.domain.usecases.series.disccoverSeries

import com.example.baseproject.domain.utils.ApiHandler
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesList
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class DiscoverSeriesUseCaseImpl(
    private val apiService: TMDBService
) : DiscoverSeriesUseCase, ApiHandler {
    override fun execute(
        airDateGte: String?,
        airDateLte: String?,
        firstAirDateYear: Int?,
        firstAirDateGte: String?,
        firstAirDateLte: String?,
        includeAdult: Boolean?,
        includeNullFirstAirDates: Boolean?,
        language: String?,
        page: Int?,
        screenedTheatrically: Boolean?,
        sortBy: String?,
        timezone: String?,
        voteAverageGte: Float?,
        voteAverageLte: Float?,
        voteCountGte: Float?,
        voteCountLte: Float?,
        watchRegion: String?,
        withCompanies: String?,
        withGenres: String?,
        withKeywords: String?,
        withNetworks: Int?,
        withOriginCountry: String?,
        withOriginalLanguage: String?,
        withRuntimeGte: Int?,
        withRuntimeLte: Int?,
        withStatus: String?,
        withWatchMonetizationTypes: String?,
        withWatchProviders: String?,
        withoutCompanies: String?,
        withoutGenres: String?,
        withoutKeywords: String?,
        withoutWatchProviders: String?,
        withType: String?
    ): Flow<ResponseStatus<SeriesList>> {
        return flow {
            emit(handleApi { apiService.discoverSeries(
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
                voteAverageGte = voteAverageGte,
                voteAverageLte = voteAverageLte,
                voteCountGte = voteCountGte,
                voteCountLte = voteCountLte,
                watchRegion = watchRegion,
                withCompanies = withCompanies,
                withGenres = withGenres,
                withKeywords = withKeywords,
                withNetworks = withNetworks,
                withOriginCountry = withOriginCountry,
                withOriginalLanguage = withOriginalLanguage,
                withRuntimeGte = withRuntimeGte,
                withRuntimeLte = withRuntimeLte,
                withStatus = withStatus,
                withWatchMonetizationTypes = withWatchMonetizationTypes,
                withWatchProviders = withWatchProviders,
                withoutCompanies = withoutCompanies,
                withoutGenres = withoutGenres,
                withoutKeywords = withoutKeywords,
                withoutWatchProviders = withoutWatchProviders,
                withType = withType
            ) })
        }.onStart {
            emit(ResponseStatus.Loading)
        }.catch { e ->
            emit(ResponseStatus.Error(
                message = e.message.toString()
            ))
        }.flowOn(Dispatchers.IO)
    }
}