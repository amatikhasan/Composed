package com.softsense.composed.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softsense.composed.domain.model.Recipe

@Composable
fun LatestRecipes(
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
                text = "Latest Recipe",
                style = MaterialTheme.typography.titleLarge
            )
            TextButton(onClick = onViewAllClick) {
                Text("View All")
            }
        }

        recipes.forEach { recipe ->
            RecipeCard(
                recipe = recipe,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                onClick = { onRecipeClick(recipe) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}