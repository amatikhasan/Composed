package com.softsense.composed.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsense.composed.domain.usecase.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val useCase: GetRecipeUseCase) : ViewModel() {
    private val _categoryUiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val categoryUiState: StateFlow<CategoryUiState> = _categoryUiState

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            val categories = useCase.getRecipeCategory()
            _categoryUiState.value = CategoryUiState.Success(categories = categories)
        }
    }
}