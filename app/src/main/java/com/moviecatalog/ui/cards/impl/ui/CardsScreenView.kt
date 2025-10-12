package com.moviecatalog.ui.cards.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
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
import com.moviecatalog.feature.cards.api.entity.Card
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.ui.cards.impl.ui.entity.CardViewState
import com.moviecatalog.ui.cards.impl.ui.entity.CardsCallbacks
import com.moviecatalog.ui.cards.impl.ui.entity.CardsScreenViewState
import com.moviecatalog.ui.cards.impl.ui.entity.CardsViewState

@Composable
fun CardsScreenView(
    uiState: CardsScreenViewState,
    callbacks: CardsCallbacks,
) {
    when (uiState) {
        is CardsScreenViewState.Error -> {
            // Center
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${uiState.message}")
            }
        }

        CardsScreenViewState.Loading -> {
            // Center
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading...")
            }
        }

        is CardsScreenViewState.Content -> {
            CardsContentView(
                uiState = uiState.state,
                callbacks = callbacks
            )
        }
    }
}


@Composable
fun CardsContentView(
    uiState: CardsViewState,
    callbacks: CardsCallbacks,
) {
    val tabs = listOf("Trending", "Popular", "Top Rated")

    val cards = uiState.cards

    Scaffold(
        bottomBar = {
            PrimaryTabRow(
                selectedTabIndex = 0,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = 0 == index,
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
        CardsCatalogList(
            items = cards,
            onClick = callbacks.onClick,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}


@Composable
fun CardCatalogCard(
    card: CardViewState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(4.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        AsyncImage(
//            model = card.posterRes,
//            placeholder = painterResource(R.drawable.empty_card_icon),
//            error = painterResource(R.drawable.empty_card_icon),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//                .clip(RoundedCornerShape(8.dp)),
//            contentScale = ContentScale.Crop
//        )

        Text(
            text = card.id.toString(),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun CardsCatalogList(
    items: List<CardViewState>,
    onClick: (CardViewState) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
        content = {
            items(items.size) { index ->
                val card = items[index]
                CardCatalogCard(card, onClick = { onClick(card) })
            }
        }
    )
}

@Preview
@Composable
fun PreviewRenderLoadingScreen() {
    CardsScreenView(
        uiState = CardsScreenViewState.Loading,
        callbacks = CardsCallbacks(
            onClick = {},
            onCategoryChange = {},
        )
    )
}

@Preview
@Composable
fun PreviewRenderErrorScreen() {
    CardsScreenView(
        uiState = CardsScreenViewState.Error("Error"),
        callbacks = CardsCallbacks(
            onClick = {},
            onCategoryChange = {},
        )
    )
}

@Preview
@Composable
fun PreviewRenderMovieScreen() {
    CardsContentView(
        uiState = CardsViewState(
            cards = listOf(
                CardViewState(1),
                CardViewState(2),
                CardViewState(3),
                CardViewState(4),
            ),
        ),
        callbacks = CardsCallbacks(
            onClick = {},
            onCategoryChange = {},
        )
    )
}