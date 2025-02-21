package com.softsense.composed.presentation.viewModel

import com.softsense.composed.domain.model.Recipe

sealed class RecipeUiState {
    object Loading : RecipeUiState()
    data class Success(val recipes: List<Recipe> = emptyList(),
                       val searchedRecipes: List<Recipe> = emptyList(),
                       val recipeByCategory: List<Recipe> = emptyList()) : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}