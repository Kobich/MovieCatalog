package com.moviecatalog.feature.movies.impl.data

import com.moviecatalog.feature.movies.api.entity.Movie
import com.moviecatalog.R
import com.moviecatalog.feature.movies.api.entity.Category
import com.moviecatalog.feature.movies.impl.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {

    override suspend fun getMovies(category: Category): List<Movie> {
        return withContext(Dispatchers.IO) {
            delay(1000L)
            movies.filter { category == it.category }
        }
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return withContext(Dispatchers.IO) {
            delay(1000L)
            movies.find { it.id == id }
        }
    }

    private val movies = listOf(
        Movie(
            id = 1,
            title = "Fury",
            posterRes = R.drawable.poster_fury,
            description = "Военный драма о танкисте в конце Второй мировой.",
            rating = 8.2,
            actors = listOf("Brad Pitt", "Logan Lerman", "Shia LaBeouf"),
            category = Category.Trending,
        ),
        Movie(
            id = 2,
            title = "Leon",
            posterRes = R.drawable.poster_lion,
            description = "Ассасин и девочка, спасающие друг друга.",
            rating = 8.5,
            actors = listOf("Jean Reno", "Natalie Portman", "Gary Oldman"),
            category = Category.Trending,
        ),
        Movie(
            id = 3,
            title = "Akira",
            posterRes = R.drawable.poster_akira,
            description = "Постапокалиптический аниме-шедевр о суперсиле.",
            rating = 8.7,
            actors = emptyList(),
            category = Category.Trending,
        ),
        Movie(
            id = 4,
            title = "Legend",
            posterRes = R.drawable.poster_legend,
            description = "История братьев Крэй в Лондоне 1960-х.",
            rating = 8.1,
            actors = listOf("Tom Hardy", "Emily Browning"),
            category = Category.Popular,
        ),
        Movie(
            id = 5,
            title = "Blade Runner 2049",
            posterRes = R.drawable.poster_bladerunner2049,
            description = "Философский научно-фантастический триллер.",
            rating = 8.0,
            actors = listOf("Ryan Gosling", "Harrison Ford", "Ana de Armas"),
            category = Category.Popular,
        ),
        Movie(
            id = 6,
            title = "Lord of the Rings",
            posterRes = R.drawable.poster_lordofthering,
            description = "Эпическая сага о Кольце Всевластья.",
            rating = 9.0,
            actors = listOf("Elijah Wood", "Viggo Mortensen", "Ian McKellen"),
            category = Category.TopRated,
        ),
        Movie(
            id = 7,
            title = "Mad Max",
            posterRes = R.drawable.poster_madmax,
            description = "Постапокалиптический экшен в пустыне.",
            rating = 8.4,
            actors = listOf("Mel Gibson", "Joan Chen"),
            category = Category.TopRated,
        ),
        Movie(
            id = 8,
            title = "Pulp Fiction",
            posterRes = R.drawable.poster_pilpfiction,
            description = "Криминальная драма с переплетающимися сюжетами.",
            rating = 8.9,
            actors = listOf("John Travolta", "Uma Thurman", "Samuel L. Jackson"),
            category = Category.TopRated,
        ),
        Movie(
            id = 9,
            title = "Spider-Man",
            posterRes = R.drawable.poster_spiderman,
            description = "Юный герой справляется с ответственностью.",
            rating = 8.2,
            actors = listOf("Tobey Maguire", "Kirsten Dunst", "Willem Dafoe"),
            category = Category.TopRated,
        ),
        Movie(
            id = 10,
            title = "Star Wars",
            posterRes = R.drawable.poster_starwars,
            description = "Космическая сага о джедаях и Ситхах.",
            rating = 8.7,
            actors = listOf("Mark Hamill", "Harrison Ford", "Carrie Fisher"),
            category = Category.TopRated,
        ),
        Movie(
            id = 11,
            title = "The Godfather",
            posterRes = R.drawable.poster_thegodfather,
            description = "История семьи Корлеоне в мафии.",
            rating = 9.2,
            actors = listOf("Marlon Brando", "Al Pacino", "James Caan"),
            category = Category.TopRated,
        ),
        Movie(
            id = 12,
            title = "The Thing",
            posterRes = R.drawable.poster_thething,
            description = "Хоррор о пришельце, который принимает облик людей.",
            rating = 8.3,
            actors = listOf("Kurt Russell", "Wilford Brimley"),
            category = Category.TopRated,
        )
    )


}