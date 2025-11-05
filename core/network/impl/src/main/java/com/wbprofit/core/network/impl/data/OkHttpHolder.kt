package com.wbprofit.core.network.impl.data

import com.wbprofit.core.keystore.api.KeystoreFeature
import com.wbprofit.core.keystore.api.SecureStorageKeys
import com.wbprofit.core.network.impl.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal const val HEADER_AUTHORIZATION = "Authorization"

internal class OkHttpHolder(
    private val secureStorage: KeystoreFeature,
    private val defaultApiKey: String,
) {
    val client: OkHttpClient = OkHttpClient
        .Builder()
        .apply {
            val authInterceptor = Interceptor { chain ->
                val originalRequest = chain.request()
                val hasAuthorization = originalRequest.header(HEADER_AUTHORIZATION) != null

                val requestBuilder = originalRequest.newBuilder()
                val apiKey = secureStorage.read(SecureStorageKeys.API_KEY)?.takeIf { it.isNotBlank() }
                    ?: defaultApiKey
                if (!hasAuthorization && apiKey.isNotBlank()) {
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
        }.build()
}
