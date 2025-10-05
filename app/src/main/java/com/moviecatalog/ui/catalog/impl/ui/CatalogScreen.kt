package com.moviecatalog.ui.catalog.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavHostController
import com.moviecatalog.ui.catalog.impl.ui.entity.MovieCallbacks

@Composable
fun CatalogScreen(
    navController: NavHostController,
    vm: CatalogViewModel = koinViewModel(),
) {
    val uiState = vm.uiState.collectAsState()
    val callbacks = MovieCallbacks(
        onMovieClick = { movie -> navController.navigate("details/${movie.id}") },
        onCategoryChange = { category -> vm.updateCategory(category) },
    )
    CatalogScreenView(uiState.value, callbacks)
}
