package com.example.pdm_00097524.JSONPlaceholder.screens.PostDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdm_00097524.JSONPlaceholder.components.PostDetailCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostDetailViewModel = viewModel()
){
    val post by viewModel.post.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(postId) {
        viewModel.loadPostById(postId)
    }

    if(loading){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Post con id: ${post?.id}") },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Cyan)
                )
            }
        ) { paddingValues ->
            CircularProgressIndicator(modifier = Modifier.padding(paddingValues))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Post con id: ${post?.id}") },
                colors = TopAppBarDefaults.topAppBarColors(Color.Cyan)
            )
        }
    ) { paddingValues ->post?.let {
        Box(modifier = Modifier.padding(paddingValues)) {
            PostDetailCard(post = it)
        }
    }
    }
}