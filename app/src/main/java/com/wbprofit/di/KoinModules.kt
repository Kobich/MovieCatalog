package com.wbprofit.di

import com.wbprofit.core.network.impl.di.networkFeatureModule
import com.wbprofit.feature.cards.impl.di.cardsFeatureModule
import com.wbprofit.ui.card.impl.di.cardDetailsModule
import com.wbprofit.ui.cards.impl.di.cardsModule
import com.wbprofit.ui.main.impl.di.mainModule
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
