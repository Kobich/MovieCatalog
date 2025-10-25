package com.wbprofit.core.secure.impl.di

import com.wbprofit.core.secure.impl.SecureKeystoreStorageImpl
import com.wbprofit.core.utils.secure.api.SecureKeystoreStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val secureStorageModule = module {
    single<SecureKeystoreStorage> {
        SecureKeystoreStorageImpl(context = androidContext())
    }
}
