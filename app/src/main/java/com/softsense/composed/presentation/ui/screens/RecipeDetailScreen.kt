package com.softsense.composed.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.softsense.composed.R
import com.softsense.composed.domain.model.Recipe
import com.softsense.composed.presentation.ui.components.ErrorMessage
import com.softsense.composed.presentation.ui.components.LoadingIndicator
import com.softsense.composed.presentation.viewModel.RecipeUiState
import com.softsense.composed.presentation.viewModel.RecipeViewModel
import com.softsense.composed.ui.theme.ComposedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {
    LaunchedEffect(recipeId) {
        recipeViewModel.getSingleRecipe(recipeId)
    }
    val recipeUiState by recipeViewModel.recipeUiState.collectAsState()
    val recipeName by remember { derivedStateOf { (recipeUiState as? RecipeUiState.Success)?.recipe?.name } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(recipeName ?: "Recipe Detail")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
               /* actions = {
                    var isFavorite by remember { mutableStateOf(false) }
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites"
                        )
                    }
                }*/
            )
        }
    ) { paddingValues ->
        when(recipeUiState){
            is RecipeUiState.Error -> {
                ErrorMessage(message ="Error loading recipe")
            }
            RecipeUiState.Loading -> {
                LoadingIndicator()
            }
            is RecipeUiState.Success -> {
                val recipe = (recipeUiState as RecipeUiState.Success).recipe
                Column(
                    modifier = modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Recipe Image
                    AsyncImage(
                        model = recipe?.image,
                        contentDescription = recipe?.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Recipe Title
                    Text(
                        text = recipe?.name ?: "Unknown",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Recipe Info Row
                    RecipeInfoRow(recipe = recipe!!)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Description Section
                    Text(
                        text = "Instructions",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    for (instruction in recipe.instructions) {
                        Text(
                            text = instruction,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    // Ingredients Section
                    IngredientsSection(ingredients = recipe.ingredients)
                }
            }
        }
    }
}

@Composable
private fun RecipeInfoRow(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RecipeInfoItem(
            icon = painterResource(id = R.drawable.recipe_splash_icon),
            label = "${recipe.cookTimeMinutes} min"
        )
        RecipeInfoItem(
            icon = painterResource(id = R.drawable.recipe_splash_icon),
            label = "${recipe.caloriesPerServing} cal"
        )
        RecipeInfoItem(
            icon = painterResource(id = R.drawable.recipe_splash_icon),
            label = "${recipe.servings} person"
        )
    }
}

@Composable
private fun RecipeInfoItem(
    icon: Painter,
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun IngredientsSection(
    modifier: Modifier = Modifier,
    ingredients: List<String>,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ingredients Added",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        for (ingredient in ingredients) {
            IngredientItem(
                icon = painterResource(id = R.drawable.recipe_splash_icon),
                name = ingredient,
            )
        }
    }
}

@Composable
private fun IngredientItem(
    icon: Painter,
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = name,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    ComposedTheme {
        RecipeDetailScreen(
            recipeId = 1,
            onBackClick = {  })
    }
}