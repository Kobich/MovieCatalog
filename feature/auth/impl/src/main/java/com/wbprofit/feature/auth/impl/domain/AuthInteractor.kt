package com.wbprofit.feature.auth.impl.domain

import com.wbprofit.feature.auth.api.AuthVerificationResult

internal class AuthInteractor(
    private val repo: AuthRepository,
) {
    suspend fun verifyToken(apiKey: String): AuthVerificationResult = repo.verifyToken(apiKey)
    suspend fun logout() = repo.logout()
}
