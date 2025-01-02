package com.example.baseproject.domain.remote.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class NetworkConnectionInterceptor(
    private val context: Context,
    private val allowVpn: Boolean,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected(context)) {
            throw IOException("No internet connection")
        }

        if (!allowVpn && isUsingVpn(context)) {
            throw IOException("VPN is not allowed for this operation")
        }

        return chain.proceed(chain.request())
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun isUsingVpn(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}
