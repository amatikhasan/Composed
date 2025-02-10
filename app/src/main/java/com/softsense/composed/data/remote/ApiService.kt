package com.softsense.composed.data.remote

import com.softsense.composed.domain.model.Category
import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.domain.model.RecipeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    @GET("recipes/{id}")
    suspend fun getRecipeById(id: Int): Recipe

    @GET("recipes/tag/{category}")
    suspend fun getRecipeByCategory(category: String): RecipeResponse

    @GET("recipes/tags")
    suspend fun getRecipeCategory(): List<String>
}