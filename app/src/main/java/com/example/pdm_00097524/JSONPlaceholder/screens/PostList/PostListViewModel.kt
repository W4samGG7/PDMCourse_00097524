package com.example.pdm_00097524.JSONPlaceholder.screens.PostList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdm_00097524.JSONPlaceholder.data.repositories.PostRepository.PostApiRepository
import com.example.pdm_00097524.JSONPlaceholder.data.repositories.PostRepository.PostRepository
import com.example.pdm_00097524.JSONPlaceholder.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel : ViewModel(){

    private val postRepository : PostRepository = PostApiRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())

    val posts = _posts.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)

    val loading = _loading.asStateFlow()

    private val _saving = MutableStateFlow<Boolean>(false)

    val saving = _saving.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)

    val error = _error.asStateFlow()

    private val _saveMessage = MutableStateFlow<String?>(null)

    val saveMessage = _saveMessage.asStateFlow()

    fun loadPost(){
        viewModelScope.launch {
            _error.value = null
            _loading.value = true

            postRepository.getPost()
                .onSuccess { posts ->
                    _posts.value = posts
                }
                .onFailure { error -> 
                    _error.value = "Error al cargar posts: ${error}"
                }
            _loading.value = false
        }
    }

    init {
        loadPost()
    }

    fun createPost(title: String, body: String, userId: Int){
        viewModelScope.launch {
            _saving.value = true

            postRepository.createPost(title,body,userId)
                .onSuccess { post ->
                    _saveMessage.value = "El post fue creado con id:${post.id}"
                }
                .onFailure { error ->
                    _saveMessage.value = "Error al crear el post: ${error.message}"
                }

            _saving.value = false
        }
    }

    fun clearMessage(){
        _saveMessage.value = null
    }


}