package com.softsense.composed.domain.usecase

import com.softsense.composed.domain.model.PostWithUser
import com.softsense.composed.domain.repository.PostRepository

class GetPostsWithUsersUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<PostWithUser> {
        return repository.getPostsWithUsers()
    }
}