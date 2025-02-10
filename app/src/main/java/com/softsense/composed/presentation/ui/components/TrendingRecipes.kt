package com.softsense.composed.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softsense.composed.domain.model.Recipe

@Composable
fun TrendingRecipes(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Trending Recipe 🔥",
                style = MaterialTheme.typography.titleLarge
            )
            TextButton(onClick = onViewAllClick) {
                Text("View All")
            }
        }
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val modifier = Modifier
            .width(280.dp)
            .height(180.dp)

            items(recipes) { recipe ->
                RecipeCard(recipe = recipe, modifier = modifier,  onClick = { onRecipeClick(recipe) })
            }
        }
    }
}