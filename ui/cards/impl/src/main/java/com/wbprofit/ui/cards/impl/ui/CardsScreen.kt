package com.wbprofit.ui.cards.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.wbprofit.ui.cards.impl.ui.entity.CardsCallbacks
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardsScreen(
    navController: NavHostController,
    vm: CardsViewModel = koinViewModel(),
) {
    val uiState = vm.uiState.collectAsState()
    val callbacks = CardsCallbacks(
        onClick = { nmId -> navController.navigate("card/$nmId") },
        onRefresh = { vm.refresh() },
    )
    CardsScreenView(uiState.value, callbacks)
}
