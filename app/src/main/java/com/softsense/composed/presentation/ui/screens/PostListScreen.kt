package com.softsense.composed.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.softsense.composed.presentation.ui.components.AppBar
import com.softsense.composed.presentation.ui.components.PostCard
import com.softsense.composed.presentation.viewModel.PostUiState
import com.softsense.composed.presentation.viewModel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(viewModel: PostViewModel = hiltViewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar()
        }
    ) { paddingValues ->
        // The Box here applies the inner padding provided by the Scaffold.
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when (uiState) {
                is PostUiState.Loading -> {
                    // Display a loading indicator.
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is PostUiState.Error -> {
                    // Show an error message.
                    val message = (uiState as PostUiState.Error).message
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Error: $message", color = MaterialTheme.colorScheme.error)
                    }
                }
                is PostUiState.Success -> {
                    // Display the list of posts.
                    val posts = (uiState as PostUiState.Success).posts
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(posts) { postWithUser ->
                            PostCard(postWithUser, navController)
                        }
                    }
                }
            }
        }
    }
}