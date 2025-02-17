package com.softsense.composed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.softsense.composed.presentation.ui.screens.AllRecipesScreen
import com.softsense.composed.presentation.ui.screens.HomeScreen
import com.softsense.composed.presentation.ui.screens.RecipeDetailScreen
import com.softsense.composed.ui.theme.ComposedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            ComposedTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph()
                }
            }
        }
    }
}

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("recipeDetail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            RecipeDetailScreen(
                onBackClick = { navController.popBackStack() },
            )
        }
        composable("allRecipes/{category}") {  backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            AllRecipesScreen(
                category = category,
                navController = navController
            )
        }
        composable("allRecipes") {
            AllRecipesScreen(
                category = null,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ComposedTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background,
            ) {
                HomeScreen(navController = rememberNavController())
            }
        }
    }
}