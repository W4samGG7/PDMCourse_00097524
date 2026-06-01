package com.example.pdm_00097524.JSONPlaceholder.data.repositories.PostRepository

import com.example.pdm_00097524.JSONPlaceholder.model.Post

interface PostRepository{
    suspend fun getPost(): Result<List<Post>>

    suspend fun createPost(
        title: String,
        body: String,
        userId: Int
    ): Result<Post>

    suspend fun getPostById(
        id: Int
    ) : Result<Post>

}