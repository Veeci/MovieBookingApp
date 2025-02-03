package com.example.baseproject.domain.utils

object CacheManager {
    private val cacheData = mutableMapOf<String, Any>()

    fun getFromCache(key: String): Any? {
        return cacheData[key]
    }

    fun addToCache(
        key: String,
        value: Any,
    ) {
        cacheData[key] = value
    }

    fun evictFromCache(key: String) {
        cacheData.remove(key)
    }
}
