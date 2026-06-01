package com.example.pdm_00097524.JSONPlaceholder.data.repositories.PostRepository


import com.example.pdm_00097524.JSONPlaceholder.data.api.KtorClient
import com.example.pdm_00097524.JSONPlaceholder.data.api.Post.CreatePostDTO
import com.example.pdm_00097524.JSONPlaceholder.data.api.Post.PostDTO
import com.example.pdm_00097524.JSONPlaceholder.data.api.Post.toModel
import com.example.pdm_00097524.JSONPlaceholder.model.Post
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostApiRepository : PostRepository {

    override suspend fun getPost(): Result<List<Post>> {
        try {
            val response: List<PostDTO> = KtorClient.client.get("posts").body()

            return Result.success( response.map { postDTO -> postDTO.toModel() })
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun createPost(title: String, body: String, userId: Int): Result<Post> {
        try {
            val request = CreatePostDTO(
                title= title,
                body = body,
                userId = userId
            )

            val response: PostDTO = KtorClient.client.post("posts"){
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            return Result.success(response.toModel())
        }catch (e: Exception){
            return Result.failure(e)
        }
    }


    override suspend fun getPostById(id: Int): Result<Post> {
        try {
            val response: PostDTO = KtorClient.client.get("posts/$id") {
            }.body()

            return Result.success(response.toModel())

        }catch (e: Exception){
            return Result.failure(e)
        }
    }
}