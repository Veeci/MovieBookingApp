package com.example.moviebooking.data.remote.services.deepseek

import com.example.baseproject.domain.remote.BaseApiController
import com.example.moviebooking.domain.common.Const

object DeepSeekServiceController : BaseApiController<DeepSeekService>() {
    override fun getBaseUrl() = Const.deepseekUrl

    override fun getApiService() = DeepSeekService::class.java
}