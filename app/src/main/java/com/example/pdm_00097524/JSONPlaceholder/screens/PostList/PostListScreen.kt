package com.example.pdm_00097524.JSONPlaceholder.screens.PostList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pdm_00097524.JSONPlaceholder.components.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen (
    viewModel: PostListViewModel = viewModel()
){
    val posts by viewModel.posts.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val saving by viewModel.saving.collectAsState()
    val error by viewModel.error.collectAsState()
    val saveMessage by viewModel.saveMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    var showCreatePost by remember { mutableStateOf(false) }

    var titleInput by remember { mutableStateOf("") }
    var bodyInput by remember { mutableStateOf("") }

    LaunchedEffect(saveMessage) {
        saveMessage?.let {message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearMessage()
        }
    }

    if(loading){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Post")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
                )
            }
        ) { paddingValues ->
            CircularProgressIndicator(modifier = Modifier.padding(paddingValues))
        }
        return
    }

    if (error != null){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Post")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Cyan)
                )
            }
        ) { paddingValues ->
            Column(modifier =
                Modifier.padding(paddingValues).padding(16.dp).background(Color.White),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(text = "${error}")
                Button(onClick = {
                    viewModel.loadPost()
                }) {
                    Text(
                        text = "Reintentar",
                        color = Color.Cyan
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Post")
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Cyan)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = {
                    showCreatePost = true
                },
                containerColor = Color.Cyan
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añandir post")
            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(posts){ post ->
                PostCard(post)
            }
        }

        if (showCreatePost){
            AlertDialog(
                onDismissRequest = {showCreatePost = false},
                title = { Text("nuevo Post")},
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = titleInput,
                            onValueChange = {titleInput = it},
                            label = {Text("titulo")},
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = bodyInput,
                            onValueChange = {bodyInput = it},
                            label = {Text("Contenido")},
                            modifier = Modifier.fillMaxWidth(),
                            /*
                            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                                keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                            )
                            */
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (titleInput.isNotBlank() && bodyInput.isNotBlank()){
                            viewModel.createPost(title = titleInput, body = bodyInput, userId = 1)
                            titleInput = ""
                            bodyInput= ""
                            showCreatePost = false
                        }
                    }) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showCreatePost = false
                    }) {
                        Text("Cancelar")
                    }

                }
            )
        }

    }

}