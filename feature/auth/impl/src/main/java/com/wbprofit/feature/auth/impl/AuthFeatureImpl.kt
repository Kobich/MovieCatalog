package com.wbprofit.feature.auth.impl

import com.wbprofit.feature.auth.api.AuthFeature
import com.wbprofit.feature.auth.api.AuthVerificationResult
import com.wbprofit.feature.auth.impl.domain.AuthInteractor

internal class AuthFeatureImpl(
    private val authInteractor: AuthInteractor,
) : AuthFeature {
    override suspend fun verifyToken(apiKey: String): AuthVerificationResult =
        authInteractor.verifyToken(apiKey)

    override suspend fun logout() {
        authInteractor.logout()
    }
}
