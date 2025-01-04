package com.example.moviebooking.data.remote.services

import android.content.Context
import com.example.baseproject.domain.remote.network.BaseApiController

object ServiceFactory {
    fun <T: Any> getService(
        controller: BaseApiController<T>,
        context: Context,
        allowVpn: Boolean = true,
        accessToken: String? = null,
        apiKey: String? = null,
        clientId: String? = null,
        additionalHeaders: Map<String, String> = emptyMap(),
    ) : T {
        return controller.getService(
            context = context,
            allowVpn = allowVpn,
            accessToken = accessToken,
            apiKey = apiKey,
            clientId = clientId,
            additionalHeaders = additionalHeaders,
        )
    }
}