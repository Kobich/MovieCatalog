package com.moviecatalog.feature.catalog

import androidx.lifecycle.ViewModel
import com.moviecatalog.feature.catalog.data.FakeMovieRepository
import com.moviecatalog.feature.catalog.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class Category { Trending, Popular, TopRated }

class CatalogViewModel: ViewModel() {

    private val repo = FakeMovieRepository()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    fun loadCategory(category: Category) {
        _movies.value = when (category) {
            Category.Trending -> repo.getTrending()
            Category.Popular -> repo.getPopular()
            Category.TopRated -> repo.getTopRated()
        }
    }
}