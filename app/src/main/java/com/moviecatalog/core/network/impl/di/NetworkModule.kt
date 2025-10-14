package com.moviecatalog.core.network.impl.di

import com.moviecatalog.BuildConfig
import com.moviecatalog.core.network.api.NetworkFeature
import com.moviecatalog.core.network.impl.NetworkFeatureImpl
import com.moviecatalog.core.network.impl.data.MoshiHolder
import com.moviecatalog.core.network.impl.data.OkHttpHolder
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val API_KEY = "api_key"
private const val BASE_URL = "base_url"

val networkFeatureModule = module {
    single<NetworkFeature> { NetworkFeatureImpl(
        okHttpHolder = get(),
        moshiHolder = get(),
        baseUrl = get(named(BASE_URL))
    ) }

    // data
    single(named(API_KEY)) { BuildConfig.WB_API_KEY }
    single(named(BASE_URL)) { BuildConfig.WB_API_BASE_URL }
    single<OkHttpHolder> { OkHttpHolder(apiKey = get(named(API_KEY))) }
    single<MoshiHolder> { MoshiHolder() }
}
