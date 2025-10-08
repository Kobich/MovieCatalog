package com.moviecatalog.feature.products.impl.di

import com.moviecatalog.BuildConfig
import com.moviecatalog.feature.products.impl.data.remote.service.ProductsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal const val HEADER_AUTHORIZATION = "Authorization"

internal fun provideProductsRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient
): Retrofit {
    val normalizedBaseUrl = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    return Retrofit.Builder()
        .baseUrl(normalizedBaseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

internal fun provideProductsOkHttpClient(
    apiKey: String
): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .addInterceptor(provideAuthorizationInterceptor(apiKey))
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
        }
        .build()
}

internal fun provideAuthorizationInterceptor(
    apiKey: String
): Interceptor = Interceptor { chain ->
    val requestBuilder = chain.request().newBuilder()
    if (apiKey.isNotBlank()) {
        requestBuilder.addHeader(HEADER_AUTHORIZATION, apiKey)
    }
    chain.proceed(requestBuilder.build())
}

internal fun provideProductsApi(retrofit: Retrofit): ProductsApi {
    return retrofit.create(ProductsApi::class.java)
}
