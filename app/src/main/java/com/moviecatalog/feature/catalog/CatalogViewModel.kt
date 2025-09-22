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

    private val _selectedCategory = MutableStateFlow(Category.Trending)
    val selectedCategory: StateFlow<Category> = _selectedCategory.asStateFlow()

    init {
        loadCategory(Category.Trending)
    }

    fun updateCategory(category: Category) {
        _selectedCategory.value = category
        loadCategory(category)
    }

    private fun loadCategory(category: Category) {
        _movies.value = when (category) {
            Category.Trending -> repo.getTrending()
            Category.Popular -> repo.getPopular()
            Category.TopRated -> repo.getTopRated()
        }
    }
}