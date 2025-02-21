package com.softsense.composed.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.softsense.composed.R
import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.presentation.ui.components.CategorySection
import com.softsense.composed.presentation.ui.components.LatestRecipes
import com.softsense.composed.presentation.ui.components.RecipeCard
import com.softsense.composed.presentation.viewModel.CategoryUiState
import com.softsense.composed.presentation.viewModel.CategoryViewModel
import com.softsense.composed.presentation.viewModel.RecipeUiState
import com.softsense.composed.presentation.viewModel.RecipeViewModel

@Composable
fun AllRecipesScreen(
    modifier: Modifier = Modifier,
    category: String?,
    recipeViewModel: RecipeViewModel = hiltViewModel(),
    navController: NavController
) {
    val recipeUiState by recipeViewModel.recipeUiState.collectAsState()
    if (category != null)
        recipeViewModel.loadRecipeByCategory(category)

    fun onRecipeClick(recipe: Recipe) {
        navController.navigate("recipeDetail/${recipe.id}")
    }

    AllRecipesScreen(
        modifier = modifier,
        recipeUiState = recipeUiState,
        onRecipeClick = { recipe -> onRecipeClick(recipe) },
        onBackClick = { navController.popBackStack() }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AllRecipesScreen(
    modifier: Modifier = Modifier,
    recipeUiState: RecipeUiState,
    onRecipeClick: (Recipe) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Recipes")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { paddingValues ->
        Surface(modifier = modifier.fillMaxSize().padding(paddingValues),
            color = MaterialTheme.colorScheme.background) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                when (recipeUiState) {
                    is RecipeUiState.Loading -> item { LoadingIndicator() }
                    is RecipeUiState.Error -> item { ErrorMessage(recipeUiState.message) }
                    is RecipeUiState.Success -> {
                        item {
                            recipeUiState.recipes.forEach { recipe ->
                                RecipeCard(recipe = recipe,
                                    modifier = Modifier.fillMaxWidth().height(180.dp)
                                        .padding(horizontal = 16.dp).padding(bottom = 8.dp),
                                    onClick = { onRecipeClick(recipe) })
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}