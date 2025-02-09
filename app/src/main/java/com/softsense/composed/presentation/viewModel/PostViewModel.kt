package com.softsense.composed.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsense.composed.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            //wait for 2 seconds to show the loading state
            kotlinx.coroutines.delay(2000)
            try {
                val postsWithUsers = repository.getPostsWithUsers()
                _uiState.value = PostUiState.Success(postsWithUsers)
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}