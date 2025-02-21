package com.softsense.composed.presentation.viewModel

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<String> = emptyList()) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}