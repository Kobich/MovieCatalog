package com.moviecatalog.di

import com.example.feature.cards.impl.di.cardsFeatureModule
import com.example.core.network.impl.di.networkFeatureModule
import com.moviecatalog.ui.cards.impl.di.cardsModule
import com.moviecatalog.ui.card.impl.di.cardDetailsModule
import com.moviecatalog.ui.main.impl.di.mainModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(

    // Features
    networkFeatureModule,
    cardsFeatureModule,

    // UI
    cardDetailsModule,
    cardsModule,
    mainModule,
)
