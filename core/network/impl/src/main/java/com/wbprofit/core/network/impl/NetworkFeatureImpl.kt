package com.wbprofit.core.network.impl

import com.wbprofit.core.network.api.NetworkFeature
import com.wbprofit.core.network.impl.data.MoshiHolder
import com.wbprofit.core.network.impl.data.OkHttpHolder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.reflect.KClass

internal class NetworkFeatureImpl(
    private val okHttpHolder: OkHttpHolder,
    private val moshiHolder: MoshiHolder,
    private val baseUrl: String
) : NetworkFeature {
    override fun <T : Any> createApi(apiClass: KClass<T>): T {
        return createService(
            apiClass = apiClass,
            url = baseUrl,
            client = okHttpHolder.client,
            converterFactory = MoshiConverterFactory.create(moshiHolder.moshi)
        )
    }

    private fun <T : Any> createService(
        apiClass: KClass<T>,
        url: String,
        client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(apiClass.java)
    }
}
