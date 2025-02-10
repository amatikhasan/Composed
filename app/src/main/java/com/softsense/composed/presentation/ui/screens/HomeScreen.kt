package com.softsense.composed.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softsense.composed.R
import com.softsense.composed.domain.model.Category
import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.presentation.ui.components.CategorySection
import com.softsense.composed.presentation.ui.components.LatestRecipes
import com.softsense.composed.presentation.ui.components.SearchBar
import com.softsense.composed.presentation.ui.components.TrendingRecipes

@Composable
fun HomeScreen() {
    // Sample data
    val categories = remember {
        listOf(
            Category("1", "Sushi", R.drawable.food),
            Category("2", "Taco", R.drawable.food),
            Category("3", "Salad", R.drawable.food),
            Category("4", "Chicken", R.drawable.food)
        )
    }

    val trendingRecipes = remember {
        listOf(
            Recipe("1", "Semizotu Salatasi", "Korean cuisine", 30, "https://images.pexels.com/photos/2092906/pexels-photo-2092906.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
            Recipe("2", "Turkish Cuisine", "Turkey cuisine", 45, "https://images.pexels.com/photos/30350295/pexels-photo-30350295/free-photo-of-close-up-of-mafaldine-pasta-in-tomato-sauce.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize().padding(top = 24.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Let's Find a\nHealthy Recipe",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                SearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onSearch = { /* Handle search */ }
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                CategorySection(
                    categories = categories,
                    onCategoryClick = { /* Handle category click */ }
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                TrendingRecipes(
                    recipes = trendingRecipes,
                    onRecipeClick = { /* Handle recipe click */ },
                    onViewAllClick = { /* Handle view all click */ }
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                LatestRecipes(recipes = trendingRecipes, onRecipeClick = { /* Handle recipe click */ }, onViewAllClick = { /* Handle view all click */ })
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}