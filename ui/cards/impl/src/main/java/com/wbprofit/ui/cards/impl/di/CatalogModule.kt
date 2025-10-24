package com.wbprofit.ui.cards.impl.di import com.wbprofit.ui.cards.api.CardsUiFeature
import com.wbprofit.ui.cards.impl.CardsUiFeatureImpl
import com.wbprofit.ui.cards.impl.domain.CardsInteractor
import com.wbprofit.ui.cards.impl.ui.CardsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cardsModule = module {
    single<CardsUiFeature> { CardsUiFeatureImpl() }

    single { CardsInteractor(get()) }
    viewModel { CardsViewModel(interactor = get()) }
}
