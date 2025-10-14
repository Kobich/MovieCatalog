package com.moviecatalog.ui.detail.impl.di

import com.moviecatalog.ui.detail.api.CardDetailsUiFeature
import com.moviecatalog.ui.detail.impl.CardDetailsUiFeatureImpl
import com.moviecatalog.ui.detail.impl.domain.CardDetailsInteractor
import com.moviecatalog.ui.detail.impl.ui.CardDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cardDetailsModule = module {
    single<CardDetailsUiFeature> { CardDetailsUiFeatureImpl() }

    factoryOf(::CardDetailsInteractor)

    viewModel { (imtID: Long) ->
        CardDetailsViewModel(
            imtID = imtID,
            interactor = get()
        )
    }
}
