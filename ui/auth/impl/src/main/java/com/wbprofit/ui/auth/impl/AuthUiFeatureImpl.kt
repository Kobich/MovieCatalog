package com.wbprofit.ui.auth.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wbprofit.ui.auth.api.AuthUiFeature
import com.wbprofit.ui.auth.impl.ui.AuthScreen

internal class AuthUiFeatureImpl : AuthUiFeature {
    @Composable
    override fun Content(navController: NavHostController) {
        AuthScreen(navController = navController)
    }
}
