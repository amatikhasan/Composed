package com.softsense.composed.presentation.viewModel

import com.softsense.composed.domain.model.PostWithUser

sealed class PostUiState {
    object Loading : PostUiState()
    data class Success(val posts: List<PostWithUser>) : PostUiState()
    data class Error(val message: String) : PostUiState()
}