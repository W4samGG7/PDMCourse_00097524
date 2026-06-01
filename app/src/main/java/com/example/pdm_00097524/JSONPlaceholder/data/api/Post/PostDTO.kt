package com.example.pdm_00097524.JSONPlaceholder.data.api.Post

import com.example.pdm_00097524.JSONPlaceholder.model.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)

fun PostDTO.toModel() : Post{
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}