package com.wbprofit.ui.auth.impl.domain

import com.wbprofit.feature.auth.api.AuthFeature
import com.wbprofit.feature.auth.api.AuthVerificationResult

internal class AuthInteractor(
    private val authFeature: AuthFeature,
) {
    suspend fun verify(apiKey: String): AuthVerificationResult = authFeature.verifyToken(apiKey)
}
