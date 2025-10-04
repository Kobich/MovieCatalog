package com.moviecatalog.ui.catalog.di

import com.moviecatalog.ui.catalog.domain.CatalogInteractor
import com.moviecatalog.ui.catalog.ui.CatalogViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val catalogModule = module {
    single { CatalogInteractor(moviesFeature = get()) }
    viewModel { CatalogViewModel(interactor = get()) }
}
