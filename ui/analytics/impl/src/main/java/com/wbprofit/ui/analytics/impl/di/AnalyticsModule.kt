package com.wbprofit.ui.analytics.impl.di

import com.wbprofit.ui.analytics.api.AnalyticsUiFeature
import com.wbprofit.ui.analytics.impl.AnalyticsUiFeatureImpl
import com.wbprofit.ui.analytics.impl.domain.AnalyticsInteractor
import com.wbprofit.ui.analytics.impl.ui.AnalyticsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val analyticsUiModule = module {
    single<AnalyticsUiFeature> { AnalyticsUiFeatureImpl() }

    single { AnalyticsInteractor(analyticsFeature = get()) }
    viewModel { AnalyticsViewModel(interactor = get()) }
}
