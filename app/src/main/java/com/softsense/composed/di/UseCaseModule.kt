package com.softsense.composed.di

import com.softsense.composed.domain.repository.RecipeRepository
import com.softsense.composed.domain.usecase.GetRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(repository: RecipeRepository): GetRecipeUseCase =
     GetRecipeUseCase(repository)
}