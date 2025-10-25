package com.wbprofit.feature.auth.impl.di

import com.wbprofit.core.network.api.NetworkFeature
import com.wbprofit.core.network.api.create
import com.wbprofit.core.utils.secure.api.SecureKeystoreStorage
import com.wbprofit.feature.auth.api.AuthFeature
import com.wbprofit.feature.auth.impl.AuthFeatureImpl
import com.wbprofit.feature.auth.impl.data.AuthRateLimiter
import com.wbprofit.feature.auth.impl.data.AuthRepositoryImpl
import com.wbprofit.feature.auth.impl.data.network.AuthApi
import com.wbprofit.feature.auth.impl.domain.AuthInteractor
import com.wbprofit.feature.auth.impl.domain.AuthRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authFeatureModule = module {
    single<AuthFeature> { AuthFeatureImpl(get()) }

    // domain
    factoryOf(::AuthInteractor)

    // data
    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get(),
            rateLimiter = get(),
            secureStorage = get<SecureKeystoreStorage>(),
        )
    }
    single<AuthApi> { get<NetworkFeature>().create() }
    single { AuthRateLimiter() }
}
