package com.wbprofit.core.keystore.impl.di

import android.content.Context
import android.content.SharedPreferences
import com.wbprofit.core.keystore.api.KeystoreFeature
import com.wbprofit.core.keystore.impl.KeystoreFeatureImpl
import com.wbprofit.core.keystore.impl.data.EncryptionEngine
import com.wbprofit.core.keystore.impl.data.KEYSTORE_PREFERENCES_FILE
import com.wbprofit.core.keystore.impl.data.KeystoreRepositoryImpl
import com.wbprofit.core.keystore.impl.domain.KeystoreInteractor
import com.wbprofit.core.keystore.impl.domain.KeystoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val keystorePrefsQualifier = named("keystorePreferences")

val keystoreModule = module {
    single { EncryptionEngine() }
    single<SharedPreferences>(keystorePrefsQualifier) {
        androidContext().applicationContext.getSharedPreferences(
            KEYSTORE_PREFERENCES_FILE,
            Context.MODE_PRIVATE,
        )
    }
    single<KeystoreRepository> {
        KeystoreRepositoryImpl(
            preferences = get(qualifier = keystorePrefsQualifier),
            encryptionEngine = get(),
        )
    }
    single { KeystoreInteractor(get()) }
    single<KeystoreFeature> { KeystoreFeatureImpl(get()) }
}
