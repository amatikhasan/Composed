package com.softsense.composed.domain.repository

import com.softsense.composed.domain.model.PostWithUser

interface PostRepository {
    suspend fun getPostsWithUsers(): List<PostWithUser>
}