package com.wbprofit.ui.auth.impl.di

import com.wbprofit.ui.auth.api.AuthUiFeature
import com.wbprofit.ui.auth.impl.AuthUiFeatureImpl
import com.wbprofit.ui.auth.impl.domain.AuthInteractor
import com.wbprofit.ui.auth.impl.ui.AuthViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authUiModule = module {
    single<AuthUiFeature> { AuthUiFeatureImpl() }

    factoryOf(::AuthInteractor)

    viewModel {
        AuthViewModel(
            interactor = get(),
        )
    }
}
