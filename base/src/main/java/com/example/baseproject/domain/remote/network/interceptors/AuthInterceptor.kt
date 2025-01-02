package com.example.baseproject.domain.remote.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithAuth =
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        return chain.proceed(requestWithAuth)
    }
}
