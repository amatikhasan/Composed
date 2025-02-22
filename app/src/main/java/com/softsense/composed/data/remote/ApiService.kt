package com.softsense.composed.data.remote

import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.domain.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: String): Recipe

    @GET("recipes/tag/{category}")
    suspend fun getRecipeByCategory(@Path("category") category: String): RecipeResponse

    @GET("recipes/tags")
    suspend fun getRecipeCategory(): List<String>
}