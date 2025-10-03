package com.moviecatalog.di

import com.moviecatalog.feature.movies.api.MoviesFeature
import com.moviecatalog.feature.movies.impl.MoviesFeatureImpl
import com.moviecatalog.feature.movies.impl.data.MovieRepositoryImpl
import com.moviecatalog.feature.movies.impl.domain.MovieRepository
import com.moviecatalog.feature.movies.impl.domain.MoviesDataInteractor
import com.moviecatalog.ui.catalog.domain.CatalogInteractor
import com.moviecatalog.ui.catalog.ui.CatalogViewModel
import com.moviecatalog.ui.details.domain.DetailsInteractor
import com.moviecatalog.ui.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Data layer
    single<MovieRepository> { MovieRepositoryImpl() }
    single { MoviesDataInteractor(repo = get()) }

    // Feature facade
    single<MoviesFeature> { MoviesFeatureImpl(moviesDataInteractor = get()) }

    // Domain interactors
    single { CatalogInteractor(moviesFeature = get()) }
    single { DetailsInteractor(moviesFeature = get()) }

    // ViewModels
    viewModel { CatalogViewModel(interactor = get()) }
    viewModel { DetailsViewModel(interactor = get()) }
}
