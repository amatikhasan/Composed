package com.softsense.composed.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.softsense.composed.presentation.ui.components.CategorySection
import com.softsense.composed.presentation.ui.components.LatestRecipes
import com.softsense.composed.presentation.ui.components.SearchBar
import com.softsense.composed.presentation.ui.components.TrendingRecipes
import com.softsense.composed.presentation.viewModel.RecipeUiState
import com.softsense.composed.presentation.viewModel.RecipeViewModel

@Composable
fun HomeScreen(viewModel: RecipeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize().padding(top = 24.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Let's Find a\nHealthy Recipe",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                SearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onSearch = { query -> viewModel.searchRecipes(query) }
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }

            when(uiState){
                is RecipeUiState.Loading -> {
                    //CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                }
                is RecipeUiState.Error -> {
                    /*Text(
                        text = (uiState as RecipeUiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    )*/
                }
                is RecipeUiState.Success -> {
                    val successState = uiState as RecipeUiState.Success

                    item {
                        CategorySection(
                            categories = successState.categories,  // Pass data from ViewModel
                            onCategoryClick = { category -> viewModel.loadRecipeByCategory(category) }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }

                    item {
                        TrendingRecipes(
                            recipes = successState.recipes, // Pass data from ViewModel
                            onRecipeClick = { recipe -> viewModel.onRecipeSelected(recipe) },
                            onViewAllClick = { viewModel.loadRecipes() }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }

                    item {
                        LatestRecipes(
                            recipes = successState.recipes, // Pass data from ViewModel
                            onRecipeClick = { recipe -> viewModel.onRecipeSelected(recipe) },
                            onViewAllClick = { viewModel.loadRecipes() }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}