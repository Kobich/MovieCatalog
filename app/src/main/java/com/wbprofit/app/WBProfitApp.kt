package com.wbprofit.app

import android.app.Application
import com.wbprofit.BuildConfig
import com.wbprofit.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WBProfitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@WBProfitApp)
            modules(appModules)
        }
    }
}
