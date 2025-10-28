package com.wbprofit.feature.auth.impl.domain

import com.wbprofit.feature.auth.api.AuthVerificationResult
import com.wbprofit.feature.auth.impl.data.AuthRateLimiter
import com.wbprofit.feature.auth.impl.data.RateLimiterResult

internal class AuthInteractor(
    private val repo: AuthRepository,
    private val rateLimiter: AuthRateLimiter,
) {
    suspend fun verifyToken(apiKey: String): AuthVerificationResult {
        val normalizedKey = apiKey.trim()
        if (normalizedKey.isEmpty()) {
            return AuthVerificationResult.Error(
                cause = IllegalArgumentException("API key must not be blank"),
            )
        }

        return when (val limiterResult = rateLimiter.acquire()) {
            is RateLimiterResult.Denied -> AuthVerificationResult.RateLimited(limiterResult.retryAfterMillis)
            RateLimiterResult.Allowed -> repo.verifyToken(normalizedKey)
        }
    }

    suspend fun logout() {
        repo.logout()
        rateLimiter.reset()
    }
}
