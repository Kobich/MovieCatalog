package com.moviecatalog.ui.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.moviecatalog.R
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.ui.catalog.ui.entity.MovieCallbacks
import com.moviecatalog.ui.catalog.ui.entity.MovieUiState

@Composable
fun CatalogScreenView(
    uiState: MovieUiState,
    callbacks: MovieCallbacks,
) {
    val tabs = listOf("Trending", "Popular", "Top Rated")

    val movies = uiState.movies
    val selectedCategory = uiState.selectedCategory

    val selectedTabIndex = when (selectedCategory) {
        Category.Trending -> 0
        Category.Popular -> 1
        Category.TopRated -> 2
    }

    Scaffold(
        bottomBar = {

            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.Companion
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = {
                            val category = when (index) {
                                0 -> Category.Trending
                                1 -> Category.Popular
                                else -> Category.TopRated
                            }
                            callbacks.onCategoryChange(category)
                        },
                        selectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0.6f
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        MovieCatalogList(
            items = movies,
            onMovieClick = callbacks.onMovieClick,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}



@Composable
fun MovieCatalogCard(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(4.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = movie.posterRes,
            placeholder = painterResource(R.drawable.empty_card_icon),
            error = painterResource(R.drawable.empty_card_icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.title,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun MovieCatalogList(
    items: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
        content = {
            items(items.size) { index ->
                val movie = items[index]
                MovieCatalogCard(movie, onClick = { onMovieClick(movie) })
            }
        }
    )
}

@Preview
@Composable
fun PreviewRenderMovieScreen() {
    CatalogScreenView(
        uiState = MovieUiState(
            movies = listOf(
                Movie(1, "Fury", R.drawable.poster_fury, "", 0.0, emptyList(), category = Category.Popular),
                Movie(2, "Leon", R.drawable.poster_lion, "", 0.0, emptyList(), category = Category.Popular),
                Movie(3, "Akira", R.drawable.poster_akira, "", 0.0, emptyList(), category = Category.Popular),
            ),
            selectedCategory = Category.Trending,
        ),
        callbacks = MovieCallbacks(
            onMovieClick = {},
            onCategoryChange = {},
        )
    )
}