package com.softsense.composed.data.repository
import com.softsense.composed.data.remote.ApiService
import com.softsense.composed.domain.model.PostWithUser
import com.softsense.composed.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override suspend fun getPostsWithUsers(): List<PostWithUser> {
        // Fetch posts and users from the API
        val posts = apiService.getPosts()
        val users = apiService.getUsers()
        // Create a map of userId to User for quick lookup.
        val userMap = users.associateBy { it.id }
        // For each post, get the corresponding user (if available).
        return posts.mapNotNull { post ->
            userMap[post.userId]?.let { user ->
                PostWithUser(post = post, user = user)
            }
        }
    }
}