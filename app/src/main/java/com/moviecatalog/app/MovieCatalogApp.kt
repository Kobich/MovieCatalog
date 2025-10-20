package com.moviecatalog.app

import android.app.Application
import com.moviecatalog.BuildConfig
import com.moviecatalog.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieCatalogApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@MovieCatalogApp)
            modules(appModules)
        }

    }
}
