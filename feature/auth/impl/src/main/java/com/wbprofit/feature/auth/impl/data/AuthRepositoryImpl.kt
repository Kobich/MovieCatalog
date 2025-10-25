package com.wbprofit.feature.auth.impl.data

import com.wbprofit.core.utils.secure.api.SecureKeystoreStorage
import com.wbprofit.core.utils.secure.api.SecureStorageKeys
import com.wbprofit.feature.auth.api.AuthVerificationResult
import com.wbprofit.feature.auth.impl.data.network.AuthApi
import com.wbprofit.feature.auth.impl.domain.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.concurrent.TimeUnit

private const val DEFAULT_RETRY_AFTER_MILLIS = 30_000L

internal class AuthRepositoryImpl(
    private val api: AuthApi,
    private val rateLimiter: AuthRateLimiter,
    private val secureStorage: SecureKeystoreStorage,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AuthRepository {

    override suspend fun verifyToken(apiKey: String): AuthVerificationResult = withContext(dispatcher) {
        val normalizedKey = apiKey.trim()
        if (normalizedKey.isEmpty()) {
            return@withContext AuthVerificationResult.Error(
                cause = IllegalArgumentException("API key must not be blank"),
            )
        }

        when (val result = rateLimiter.acquire()) {
            is RateLimiterResult.Denied -> return@withContext AuthVerificationResult.RateLimited(result.retryAfterMillis)
            RateLimiterResult.Allowed -> Unit
        }

        val response = runCatching { api.verifyToken(normalizedKey) }
            .getOrElse { throwable ->
                return@withContext AuthVerificationResult.Error(cause = throwable)
            }

        when {
            response.isSuccessful -> handleSuccess(normalizedKey)
            response.code() == 401 -> AuthVerificationResult.Unauthorized
            response.code() == 429 -> handleRateLimited(response)
            else -> AuthVerificationResult.Error(statusCode = response.code())
        }
    }

    private fun handleSuccess(apiKey: String): AuthVerificationResult {
        secureStorage.save(SecureStorageKeys.API_KEY, apiKey)
        return AuthVerificationResult.Success
    }

    private fun handleRateLimited(response: Response<*>): AuthVerificationResult {
        val retryAfterHeader = response.headers()["Retry-After"]
        val retryAfterMillis = retryAfterHeader?.toLongOrNull()?.let { TimeUnit.SECONDS.toMillis(it) }
            ?: DEFAULT_RETRY_AFTER_MILLIS
        return AuthVerificationResult.RateLimited(retryAfterMillis)
    }

    override suspend fun logout() = withContext(dispatcher) {
        secureStorage.remove(SecureStorageKeys.API_KEY)
        rateLimiter.reset()
    }
}
