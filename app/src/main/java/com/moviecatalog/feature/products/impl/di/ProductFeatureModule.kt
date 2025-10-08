package com.moviecatalog.feature.products.impl.di

import com.moviecatalog.BuildConfig
import com.moviecatalog.feature.products.api.ProductFeature
import com.moviecatalog.feature.products.impl.ProductFeatureImpl
import com.moviecatalog.feature.products.impl.data.ProductRepositoryImpl
import com.moviecatalog.feature.products.impl.data.datasource.ProductsNetworkDataSource
import com.moviecatalog.feature.products.impl.data.remote.datasource.ProductsRemoteDataSource
import com.moviecatalog.feature.products.impl.data.remote.service.ProductsApi
import com.moviecatalog.feature.products.impl.domain.ProductDataInteractor
import com.moviecatalog.feature.products.impl.domain.ProductRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val PRODUCTS_OKHTTP = "productsOkHttp"
private const val PRODUCTS_RETROFIT = "productsRetrofit"

val productFeatureModule = module {
    single(named(PRODUCTS_OKHTTP)) {
        provideProductsOkHttpClient(apiKey = BuildConfig.WB_TEST_API_KEY)
    }
    single(named(PRODUCTS_RETROFIT)) {
        provideProductsRetrofit(
            baseUrl = BuildConfig.WB_API_BASE_URL,
            okHttpClient = get(named(PRODUCTS_OKHTTP))
        )
    }
    single<ProductsApi> {
        provideProductsApi(retrofit = get(named(PRODUCTS_RETROFIT)))
    }
    single { ProductsRemoteDataSource(api = get()) }
    single { ProductsNetworkDataSource(remoteDataSource = get()) }
    single<ProductRepository> { ProductRepositoryImpl(networkDataSource = get()) }
    single { ProductDataInteractor(repo = get()) }
    single<ProductFeature> { ProductFeatureImpl(productsDataInteractor = get()) }
}
