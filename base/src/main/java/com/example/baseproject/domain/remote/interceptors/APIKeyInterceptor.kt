package com.example.baseproject.domain.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKey =
            chain.request().newBuilder()
                .addHeader("x-api-key", apiKey)
                .build()
        return chain.proceed(requestWithApiKey)
    }
}
