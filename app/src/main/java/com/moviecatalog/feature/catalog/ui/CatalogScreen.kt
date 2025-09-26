package com.moviecatalog.feature.catalog.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.moviecatalog.feature.catalog.ui.entity.MovieCallbacks

@Composable
fun CatalogScreen(
    navController: NavHostController,
    vm: CatalogViewModel = viewModel(),
) {
    val uiState = vm.uiState.collectAsState()
    val callbacks = MovieCallbacks(
        onMovieClick = { movie -> navController.navigate("details/${movie.id}") },
        onCategoryChange = { category -> vm.updateCategory(category) },
    )
    CatalogScreenView(uiState.value, callbacks)
}

