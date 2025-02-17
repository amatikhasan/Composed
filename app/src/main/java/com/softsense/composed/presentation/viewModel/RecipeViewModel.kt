package com.softsense.composed.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.domain.usecase.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val useCase: GetRecipeUseCase) : ViewModel() {
    private val _recipeUiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val recipeUiState: StateFlow<RecipeUiState> = _recipeUiState

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            try {
                val response = useCase.invoke()
                _recipeUiState.value = RecipeUiState.Success(response.recipes) // Extract recipes from response
            } catch (e: Exception) {
                Log.e("API_ERROR", "Exception: ${e.message}")
                _recipeUiState.value = RecipeUiState.Error("Failed to load recipes")
            }
        }
    }

    fun searchRecipes(query: String) {
        /*viewModelScope.launch {
            val searchResults = repository.searchRecipes(query)
            _uiState.update { it.copy(searchedRecipes = searchResults) }
        }*/
    }

    fun loadRecipeByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = useCase.getRecipeByCategory(category)
                _recipeUiState.value = RecipeUiState.Success(recipeByCategory = response.recipes)
            } catch (e: Exception) {
                Log.e("API_ERROR", "Exception: ${e.message}")
                _recipeUiState.value = RecipeUiState.Error("Failed to load recipes")
            }
        }
    }

    fun onRecipeSelected(recipe: Recipe) {
        // Handle recipe selection event (e.g., navigate to detail screen)
    }
}