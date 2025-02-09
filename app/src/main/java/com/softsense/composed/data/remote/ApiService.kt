package com.softsense.composed.data.remote

import com.softsense.composed.domain.model.Post
import com.softsense.composed.domain.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users")
    suspend fun getUsers(): List<User>
}