package com.moviecatalog.feature.cards.impl.di

import com.moviecatalog.feature.cards.api.CardsFeature
import com.moviecatalog.feature.cards.impl.CardsFeatureImpl
import com.moviecatalog.feature.cards.impl.data.CardsRepositoryImpl
import com.moviecatalog.feature.cards.impl.data.network.CardsApi
import com.moviecatalog.feature.cards.impl.data.network.mapper.CardsDtoMapper
import com.moviecatalog.feature.cards.impl.domain.CardsInteractor
import com.moviecatalog.feature.cards.impl.domain.CardsRepository
import com.moviecatalog.core.network.api.NetworkFeature
import com.moviecatalog.core.network.api.create
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
