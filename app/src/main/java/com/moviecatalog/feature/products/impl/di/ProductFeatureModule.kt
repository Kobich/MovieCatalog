package com.moviecatalog.feature.products.impl.di

import com.moviecatalog.feature.network.api.NetworkFeature
import com.moviecatalog.feature.network.api.create
import com.moviecatalog.feature.products.api.ProductFeature
import com.moviecatalog.feature.products.impl.ProductFeatureImpl
import com.moviecatalog.feature.products.impl.data.ProductRepositoryImpl
import com.moviecatalog.feature.products.impl.data.network.ProductsApi
import com.moviecatalog.feature.products.impl.domain.CardsInteractor
import com.moviecatalog.feature.products.impl.domain.ProductDataInteractor
import com.moviecatalog.feature.products.impl.domain.ProductRepository
import org.koin.dsl.module

val productFeatureModule = module {

    single<ProductFeature> { ProductFeatureImpl(get(), get()) }

    // domain
    single { ProductDataInteractor(repo = get()) }
    single { CardsInteractor(repo = get()) }

    // data
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<ProductsApi> { get<NetworkFeature>().create() }
}
