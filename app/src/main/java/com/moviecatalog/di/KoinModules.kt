package com.moviecatalog.di

import com.moviecatalog.feature.movies.impl.di.moviesFeatureModule
import com.moviecatalog.ui.catalog.impl.di.catalogModule
import com.moviecatalog.ui.details.impl.di.detailsModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    moviesFeatureModule,
    catalogModule,
    detailsModule,
)
