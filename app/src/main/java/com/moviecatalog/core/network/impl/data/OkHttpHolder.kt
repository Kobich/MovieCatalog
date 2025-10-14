package com.moviecatalog.core.network.impl.data

import com.moviecatalog.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal const val HEADER_AUTHORIZATION = "Authorization"

internal class OkHttpHolder(
    apiKey: String
) {
    val client : OkHttpClient = OkHttpClient.Builder()
        .apply {

            val authInterceptor = Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                if (apiKey.isNotBlank()) {
                    requestBuilder.addHeader(HEADER_AUTHORIZATION, apiKey)
                }
                chain.proceed(requestBuilder.build())
            }
            addInterceptor(authInterceptor)

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
        }
        .build()
}