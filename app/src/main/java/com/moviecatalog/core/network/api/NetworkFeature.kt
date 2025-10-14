package com.moviecatalog.core.network.api

import kotlin.reflect.KClass

interface NetworkFeature {
    fun <T : Any> createApi(apiClass: KClass<T>): T
}

inline fun <reified T : Any> NetworkFeature.create(): T = createApi(T::class)
