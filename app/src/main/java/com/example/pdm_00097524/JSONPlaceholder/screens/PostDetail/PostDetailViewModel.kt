package com.example.pdm_00097524.JSONPlaceholder.screens.PostDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pdm_00097524.JSONPlaceholder.data.repositories.PostRepository.PostApiRepository
import com.example.pdm_00097524.JSONPlaceholder.data.repositories.PostRepository.PostRepository
import com.example.pdm_00097524.JSONPlaceholder.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PostDetailViewModel : ViewModel(){

    private val postRepository: PostRepository = PostApiRepository()

    private val _post = MutableStateFlow<Post?>(null)

    val post = _post.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)

    val loading = _loading.asStateFlow()

    fun loadPostById(id: Int){
        viewModelScope.launch {
            _loading.value = true

            postRepository.getPostById(id)
                .onSuccess { post ->
                    _post.value = post
                }
            _loading.value = false
        }
    }
}