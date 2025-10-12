package com.moviecatalog.ui.cards.impl.di

import com.moviecatalog.ui.cards.api.CardsUiFeature
import com.moviecatalog.ui.cards.impl.CardsUiFeatureImpl
import com.moviecatalog.ui.cards.impl.domain.CardsInteractor
import com.moviecatalog.ui.cards.impl.ui.CardsViewModel

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cardsModule = module {
    single<CardsUiFeature> { CardsUiFeatureImpl() }

    single { CardsInteractor(get()) }
    viewModel { CardsViewModel(interactor = get()) }
}
