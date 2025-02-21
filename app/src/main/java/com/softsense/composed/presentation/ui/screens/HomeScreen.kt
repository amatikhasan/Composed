package com.softsense.composed.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.softsense.composed.presentation.ui.components.ErrorMessage
import com.softsense.composed.presentation.ui.components.LatestRecipes
import com.softsense.composed.presentation.ui.components.LoadingIndicator
import com.softsense.composed.presentation.viewModel.CategoryUiState
import com.softsense.composed.presentation.viewModel.CategoryViewModel
import com.softsense.composed.presentation.viewModel.RecipeUiState
import com.softsense.composed.presentation.viewModel.RecipeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        recipeViewModel.loadRecipes()
    }
    val recipeUiState by recipeViewModel.recipeUiState.collectAsState()
    val categoryUiState by categoryViewModel.categoryUiState.collectAsState()

    fun onCategoryClick(category: String) {
        navController.navigate("allRecipes/$category")
    }

    fun onRecipeClick(recipe: Recipe) {
        navController.navigate("recipeDetail/${recipe.id}")
    }

    fun onViewAllClick() {
        navController.navigate("allRecipes")
    }

    HomeScreenContent(
        modifier = modifier,
        recipeUiState = recipeUiState,
        categoryUiState = categoryUiState,
        onCategoryClick = { category -> onCategoryClick(category) },
        onRecipeClick = { recipe -> onRecipeClick(recipe) },
        onViewAllClick = { onViewAllClick() }
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    recipeUiState: RecipeUiState,
    categoryUiState: CategoryUiState,
    onCategoryClick: (String) -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    onViewAllClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            item {
                HomeHeader()
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            when (recipeUiState) {
                is RecipeUiState.Loading -> item { LoadingIndicator() }
                is RecipeUiState.Error -> item { ErrorMessage(recipeUiState.message) }
                is RecipeUiState.Success -> {
                    if (categoryUiState is CategoryUiState.Success) {
                        item {
                            CategorySection(
                                categories = categoryUiState.categories,
                                onCategoryClick = { category -> onCategoryClick(category) }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        item {
                            LatestRecipes(
                                recipes = recipeUiState.recipes,
                                onRecipeClick = onRecipeClick,
                                onViewAllClick = onViewAllClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.home_header_text),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}