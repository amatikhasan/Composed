package com.softsense.composed.data.repository
import android.util.Log
import com.softsense.composed.data.remote.ApiService
import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.domain.model.RecipeResponse
import com.softsense.composed.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RecipeRepository {

    override suspend fun getRecipes(): RecipeResponse {
        Log.d("TAG", "getRecipes: ")
        return apiService.getRecipes()
    }

    override suspend fun getSingleRecipe(int: Int): Recipe {
        Log.d("TAG", "getSingleRecipe: $int")
        return apiService.getRecipeById(int.toString())
    }

    override suspend fun getRecipeByCategory(category: String): RecipeResponse {
        Log.d("TAG", "getRecipeByCategory: ")
        return apiService.getRecipeByCategory(category)
    }

    override suspend fun getRecipeCategories(): List<String> {
        Log.d("TAG", "getRecipeCategories: ")
        return apiService.getRecipeCategory()
    }
}