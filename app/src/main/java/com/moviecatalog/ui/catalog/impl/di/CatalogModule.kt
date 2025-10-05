package com.moviecatalog.ui.catalog.impl.di

import com.moviecatalog.ui.catalog.api.CatalogUiFeature
import com.moviecatalog.ui.catalog.impl.CatalogUiFeatureImpl
import com.moviecatalog.ui.catalog.impl.domain.CatalogInteractor
import com.moviecatalog.ui.catalog.impl.ui.CatalogViewModel

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val catalogModule = module {
    single<CatalogUiFeature> { CatalogUiFeatureImpl() }

    single { CatalogInteractor(moviesFeature = get()) }
    viewModel { CatalogViewModel(interactor = get()) }
}
