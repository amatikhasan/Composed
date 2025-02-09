package com.softsense.composed.domain.model

data class PostWithUser(
    val post: Post,
    val user: User?
)