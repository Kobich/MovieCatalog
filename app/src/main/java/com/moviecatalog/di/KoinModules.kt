package com.moviecatalog.di

import com.moviecatalog.feature.cards.impl.di.cardsFeatureModule
import com.moviecatalog.feature.movies.impl.di.moviesFeatureModule
import com.moviecatalog.feature.network.impl.di.networkFeatureModule
import com.moviecatalog.feature.products.impl.di.productFeatureModule
import com.moviecatalog.ui.cards.impl.di.cardsModule
import com.moviecatalog.ui.catalog.impl.di.catalogModule
import com.moviecatalog.ui.detail.impl.di.cardDetailsModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(

    // Features
    networkFeatureModule,
    productFeatureModule,
    moviesFeatureModule,
    cardsFeatureModule,

    // UI
    catalogModule,
    cardDetailsModule,
    cardsModule,
)
