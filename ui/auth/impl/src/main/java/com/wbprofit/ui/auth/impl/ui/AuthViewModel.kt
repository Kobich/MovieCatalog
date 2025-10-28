package com.wbprofit.ui.auth.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbprofit.feature.auth.api.AuthVerificationResult
import com.wbprofit.ui.auth.impl.domain.AuthInteractor
import com.wbprofit.ui.auth.impl.ui.entity.AuthStatus
import com.wbprofit.ui.auth.impl.ui.entity.AuthUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AuthViewModel(
    private val interactor: AuthInteractor,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private var verificationJob: Job? = null

    fun onApiKeyChange(value: String) {
        _uiState.update { state ->
            state.copy(
                apiKey = value,
                status = when (state.status) {
                    AuthStatus.Success -> AuthStatus.Success
                    else -> AuthStatus.Idle
                },
            )
        }
    }

    fun onContinueClick() {
        val currentState = _uiState.value
        if (!currentState.isContinueEnabled || currentState.isLoading) return

        verificationJob?.cancel()
        verificationJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, status = AuthStatus.Idle) }
            val result = runCatching {
                interactor.verify(currentState.apiKey.trim())
            }.getOrElse { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        status = AuthStatus.Error(throwable.message ?: GENERIC_ERROR_MESSAGE),
                    )
                }
                return@launch
            }

            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    status = result.toStatus(),
                )
            }
        }
    }

    private fun AuthVerificationResult.toStatus(): AuthStatus = when (this) {
        AuthVerificationResult.Success -> AuthStatus.Success
        AuthVerificationResult.Unauthorized -> AuthStatus.Error(UNAUTHORIZED_MESSAGE)
        is AuthVerificationResult.RateLimited -> AuthStatus.RateLimited(retryAfterMillis)
        is AuthVerificationResult.Error -> {
            val message = cause?.message
                ?: statusCode?.let { "Ошибка сервера ($it)" }
                ?: GENERIC_ERROR_MESSAGE
            AuthStatus.Error(message)
        }
    }

    private companion object {
        const val GENERIC_ERROR_MESSAGE = "Не удалось проверить ключ"
        const val UNAUTHORIZED_MESSAGE = "Неверный API ключ"
    }
}
