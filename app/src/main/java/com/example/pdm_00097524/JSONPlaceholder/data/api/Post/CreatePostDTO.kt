package com.example.pdm_00097524.JSONPlaceholder.data.api.Post

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostDTO(
    val title: String,
    val body: String,
    val userId: Int
)