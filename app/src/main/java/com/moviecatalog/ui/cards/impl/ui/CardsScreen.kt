package com.moviecatalog.ui.cards.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavHostController
import com.moviecatalog.ui.cards.impl.ui.entity.CardsCallbacks

@Composable
fun CardsScreen(
    navController: NavHostController,
    vm: CardsViewModel = koinViewModel(),
) {
    val uiState = vm.uiState.collectAsState()
    val callbacks = CardsCallbacks(
        onClick = { card -> navController.navigate("card/${card.id}") },
        onCategoryChange = { category -> vm.refresh() },
    )
    CardsScreenView(uiState.value, callbacks)
}
