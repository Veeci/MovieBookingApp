package com.example.baseproject.domain.remote

import android.content.Context
import com.example.baseproject.domain.remote.interceptors.ApiKeyInterceptor
import com.example.baseproject.domain.remote.interceptors.AuthInterceptor
import com.example.baseproject.domain.remote.interceptors.HeadersInterceptor
import com.example.baseproject.domain.remote.interceptors.NetworkConnectionInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseApiController<T : Any> {
    companion object {
        const val TIME_OUT = 30L
    }

    fun getService(
        context: Context,
        allowVpn: Boolean = true,
        accessToken: String? = null,
        apiKey: String? = null,
        clientId: String? = null,
        isBearerToken: Boolean = false,
        additionalHeaders: Map<String, String> = emptyMap(),
    ): T {
        val builder =
            OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)

        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            },
        )

        apiKey?.let { builder.addInterceptor(ApiKeyInterceptor(it)) }

        accessToken?.let { builder.addInterceptor(AuthInterceptor(it)) }

        // customized for this project
        clientId?.let {
            builder.addInterceptor(
                Interceptor { chain ->
                    val request =
                        chain.request().newBuilder()
                            .addHeader("x-client-id", it)
                            .build()
                    chain.proceed(request)
                },
            )
        }

        if (additionalHeaders.isNotEmpty()) {
            builder.addInterceptor(HeadersInterceptor(additionalHeaders))
        }

        builder.addInterceptor(NetworkConnectionInterceptor(context, allowVpn))

        val retrofit =
            Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        @Suppress("UNCHECKED_CAST")
        return retrofit.create(getApiService()) as T
    }

    abstract fun getBaseUrl(): String

    abstract fun getApiService(): Class<*>
}
