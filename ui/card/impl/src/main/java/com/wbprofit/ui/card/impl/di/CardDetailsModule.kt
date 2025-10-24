package com.wbprofit.ui.card.impl.di

import com.wbprofit.ui.card.api.CardDetailsUiFeature
import com.wbprofit.ui.card.impl.CardDetailsUiFeatureImpl
import com.wbprofit.ui.card.impl.domain.CardDetailsInteractor
import com.wbprofit.ui.card.impl.ui.CardDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cardDetailsModule = module {
    single<CardDetailsUiFeature> { CardDetailsUiFeatureImpl() }

    factoryOf(::CardDetailsInteractor)

    viewModel { (nmId: Long) ->
        CardDetailsViewModel(
            nmId = nmId,
            interactor = get(),
        )
    }
}
