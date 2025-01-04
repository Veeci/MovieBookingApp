package com.example.moviebooking.data.remote.services.tmdb

import com.example.baseproject.domain.remote.network.BaseApiController
import com.example.moviebooking.domain.Const

object TMDBServiceController: BaseApiController<TMDBService>() {
    override fun getBaseUrl(): String = Const.tmdbUrl

    override fun getApiService() = TMDBService::class.java
}