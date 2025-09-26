package com.moviecatalog.feature.details.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.moviecatalog.R
import com.moviecatalog.feature.catalog.domain.entity.Movie
import com.moviecatalog.feature.details.ui.entity.DetailsCallbacks


@Composable
fun DetailsScreen(
    navController: NavHostController,
    movieId: Int,
    vm: DetailsViewModel = viewModel(),
) {
    LaunchedEffect(movieId) {
        vm.setMovieId(movieId)
    }

    val movie by vm.movie.collectAsState()

    val callbacks = DetailsCallbacks(
        onPosterClick = {
            // TODO:
        },
        onBackClick = {
            navController.popBackStack()
        }
    )

    movie?.let {
        DetailsScreenView(it, callbacks)
    } ?: Text("Загрузка...")

}



@Composable
fun DetailsScreenView(
    movie: Movie,
    callbacks: DetailsCallbacks
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = movie.posterRes,
                    placeholder = painterResource(R.drawable.empty_card_icon),
                    error = painterResource(R.drawable.empty_card_icon),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 3f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_stars_rating),
                        contentDescription = "Rating",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "${movie.rating}/10",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Text(
                    text = movie.description.ifEmpty { "No description available" },
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                    .padding(16.dp)
                    .align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_arrow_back_ios_24),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { callbacks.onBackClick() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val mockMovie = Movie(
        id = 1,
        title = "Inception",
        posterRes = R.drawable.empty_card_icon,
        description = "A skilled thief is offered a chance to erase his criminal past by completing an impossible mission: planting an idea into someone's subconscious.",
        rating = 8.8,
        actors = listOf("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page", "Tom Hardy")
    )

}