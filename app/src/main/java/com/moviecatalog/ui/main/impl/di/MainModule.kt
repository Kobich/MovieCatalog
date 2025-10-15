package com.moviecatalog.ui.main.impl.di

import com.moviecatalog.ui.main.api.MainUiFeature
import com.moviecatalog.ui.main.impl.MainUiFeatureImpl
import org.koin.dsl.module

val mainModule = module {
    single<MainUiFeature> {
        MainUiFeatureImpl(
            cardsUiFeature = get(),
            cardDetailsUiFeature = get(),
        )
    }
}
