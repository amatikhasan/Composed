package com.softsense.composed.domain.usecase

import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.domain.model.RecipeResponse
import com.softsense.composed.domain.repository.RecipeRepository

class GetRecipeUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(): RecipeResponse {
        return repository.getRecipes()
    }

    suspend fun getSingleRecipe(int: Int): Recipe {
        return repository.getSingleRecipe(int)
    }

    suspend fun getRecipeByCategory(category: String): RecipeResponse {
        return repository.getRecipeByCategory(category)
    }

    suspend fun getRecipeCategory(): List<String> {
        return repository.getRecipeCategories()
    }
}