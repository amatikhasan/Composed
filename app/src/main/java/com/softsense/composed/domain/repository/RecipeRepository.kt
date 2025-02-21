package com.softsense.composed.domain.repository

import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.domain.model.RecipeResponse

interface RecipeRepository {
    suspend fun getRecipes(): RecipeResponse
    suspend fun getSingleRecipe(int: Int): Recipe
    suspend fun getRecipeByCategory(category: String): RecipeResponse
    suspend fun getRecipeCategories(): List<String>
}