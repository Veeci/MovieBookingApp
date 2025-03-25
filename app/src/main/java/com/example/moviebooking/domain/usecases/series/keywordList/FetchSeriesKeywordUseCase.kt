package com.example.moviebooking.domain.usecases.series.keywordList

import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.data.remote.entities.tmdb.Keyword
import kotlinx.coroutines.flow.Flow

interface FetchSeriesKeywordUseCase {
    fun execute(seriesId: String): Flow<ResponseStatus<Keyword>>
}