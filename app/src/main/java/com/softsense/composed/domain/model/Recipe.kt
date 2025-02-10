package com.softsense.composed.domain.model

data class Recipe(
    val id: String,
    val name: String,
    val cuisine: String,
    val duration: Int,
    val imageUrl: String
)

data class Category(
    val id: String,
    val name: String,
    val icon: Int
)