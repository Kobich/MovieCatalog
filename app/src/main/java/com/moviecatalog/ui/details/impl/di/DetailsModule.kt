package com.moviecatalog.ui.details.impl.di

import com.moviecatalog.ui.details.api.DetailsUiFeature
import com.moviecatalog.ui.details.impl.domain.DetailsInteractor
import com.moviecatalog.ui.details.impl.DetailsUiFeatureImpl
import com.moviecatalog.ui.details.impl.ui.DetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    single<DetailsUiFeature> { DetailsUiFeatureImpl() }

    // ViewModel для управления жизненным циклом скоупа
    viewModel { DetailsScopeViewModel() }

    scope<DetailsScopeViewModel> {
        scoped { DetailsInteractor(moviesFeature = get()) }
        viewModel { (movieId: Int) -> DetailsViewModel(movieId = movieId, interactor = get()) }
    }
}
