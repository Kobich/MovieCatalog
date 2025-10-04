package com.moviecatalog.app

import android.app.Application
import com.moviecatalog.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieCatalogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieCatalogApp)
            modules(appModules)
        }
    }
}
