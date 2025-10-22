package com.wbprofit.feature.cards.impl.di

import com.wbprofit.feature.cards.impl.CardsFeatureImpl
import com.wbprofit.feature.cards.impl.data.CardsRepositoryImpl
import com.wbprofit.feature.cards.impl.data.network.CardsApi
import com.wbprofit.feature.cards.api.CardsFeature
import com.wbprofit.feature.cards.impl.data.network.mapper.CardsDtoMapper
import com.wbprofit.feature.cards.impl.domain.CardsInteractor
import com.wbprofit.feature.cards.impl.domain.CardsRepository
import com.wbprofit.core.network.api.NetworkFeature
import com.wbprofit.core.network.api.create
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cardsFeatureModule = module {

    single<CardsFeature> { CardsFeatureImpl(get()) }

    // domain
    single { CardsInteractor(repo = get()) }

    // data
    single<CardsRepository> { CardsRepositoryImpl(get(), get()) }
    single<CardsApi> { get<NetworkFeature>().create() }
    factoryOf(::CardsDtoMapper)
}
