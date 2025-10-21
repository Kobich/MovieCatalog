package com.example.feature.cards.impl.di

import com.example.feature.cards.impl.CardsFeatureImpl
import com.example.feature.cards.impl.data.CardsRepositoryImpl
import com.example.feature.cards.impl.data.network.CardsApi
import com.example.feature.cards.api.CardsFeature
import com.example.feature.cards.impl.data.network.mapper.CardsDtoMapper
import com.example.feature.cards.impl.domain.CardsInteractor
import com.example.feature.cards.impl.domain.CardsRepository
import com.example.core.network.api.NetworkFeature
import com.example.core.network.api.create
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
