package com.example.moviebooking.data.remote.services.vietqr

import com.example.baseproject.domain.remote.BaseApiController
import com.example.moviebooking.domain.common.Const

object VietQRServiceController: BaseApiController<VietQRService>() {
    override fun getBaseUrl() = Const.vietqrUrl

    override fun getApiService() = VietQRService::class.java
}