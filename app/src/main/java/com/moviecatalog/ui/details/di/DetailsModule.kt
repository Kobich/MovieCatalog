package com.moviecatalog.ui.details.di

import com.moviecatalog.ui.details.domain.DetailsInteractor
import com.moviecatalog.ui.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    single { DetailsInteractor(moviesFeature = get()) }
    viewModel { (movieId: Int) -> DetailsViewModel(movieId = movieId, interactor = get()) }
}
