package com.example.moviebooking.domain.di

import com.example.moviebooking.data.remote.services.ServiceFactory
import com.example.moviebooking.data.remote.services.deepseek.DeepSeekService
import com.example.moviebooking.data.remote.services.deepseek.DeepSeekServiceController
import com.example.moviebooking.data.remote.services.tmdb.TMDBService
import com.example.moviebooking.data.remote.services.tmdb.TMDBServiceController
import com.example.moviebooking.data.remote.services.vietqr.VietQRService
import com.example.moviebooking.data.remote.services.vietqr.VietQRServiceController
import com.example.moviebooking.domain.common.Const
import org.koin.dsl.module

object ServiceModule {
    fun init() = module {
        factory<TMDBService> {
            ServiceFactory.getService(
                context = get(),
                controller = TMDBServiceController,
                accessToken = Const.tmdbAccessToken
            )
        }
        factory<VietQRService> {
            ServiceFactory.getService(
                context = get(),
                controller = VietQRServiceController,
                apiKey = Const.vietqrApiKey,
                clientId = Const.vietqrClientId
            )
        }
        factory<DeepSeekService> {
            ServiceFactory.getService(
                context = get(),
                controller = DeepSeekServiceController,
                accessToken = Const.deepseekApiKey
            )
        }
    }
}