package com.softsense.composed.presentation.viewModel

import com.softsense.composed.domain.model.Category
import com.softsense.composed.domain.model.Recipe

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<String> = emptyList()) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}