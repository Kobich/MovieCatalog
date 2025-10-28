package com.wbprofit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wbprofit.base.ui.theme.MovieCatalogTheme
import com.wbprofit.core.keystore.api.KeystoreFeature
import com.wbprofit.core.keystore.api.SecureStorageKeys
import com.wbprofit.ui.auth.api.AuthNavRoute
import com.wbprofit.ui.auth.api.AuthUiFeature
import com.wbprofit.ui.main.api.MainNavRoute
import com.wbprofit.ui.main.api.MainUiFeature
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val mainUiFeature by inject<MainUiFeature>()
    private val authUiFeature by inject<AuthUiFeature>()
    private val secureStorage by inject<KeystoreFeature>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCatalogTheme {
                val navController = rememberNavController()
                val startDestination = remember {
                    if (secureStorage.contains(SecureStorageKeys.API_KEY)) {
                        MainNavRoute.MAIN
                    } else {
                        AuthNavRoute.AUTH
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = startDestination,
                ) {
                    composable(AuthNavRoute.AUTH) {
                        authUiFeature.Content(navController)
                    }
                    composable(MainNavRoute.MAIN) {
                        mainUiFeature.Content(
                            onLogout = {
                                navController.navigate(AuthNavRoute.AUTH) {
                                    popUpTo(MainNavRoute.MAIN) { inclusive = true }
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}
