package com.moviecatalog.feature.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.moviecatalog.R
import com.moviecatalog.feature.catalog.models.Movie

@Composable
fun CatalogScreen(
    navController: NavHostController,
    vm: CatalogViewModel = viewModel(),
) {

    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Trending", "Popular", "Top Rated")

    val movies by vm.movies.collectAsState()

    LaunchedEffect(selectedTab) {
        vm.loadCategory(
            when (selectedTab) {
                0 -> Category.Trending
                1 -> Category.Popular
                else -> Category.TopRated
            }
        )
    }


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }

            MovieCatalogList(
                items = movies,
                onMovieClick = { navController.navigate("details") },
            )
        }
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
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize(),
        content = {
            items(items.size) { index ->
                val movie = items[index]
                MovieCatalogCard(movie, onClick = { onMovieClick(movie) })
            }
        }
    )
}