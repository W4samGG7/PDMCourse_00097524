package com.example.pdm_00097524.JSONPlaceholder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.pdm_00097524.JSONPlaceholder.screens.PostDetail.PostDetailScreen
import com.example.pdm_00097524.JSONPlaceholder.screens.PostList.PostListScreen

@Composable
fun PostApp(){
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryProvider = entryProvider {
            entry<Routes.Home>{
                PostListScreen(NavigateToDetail = {postId->
                    backStack.add(Routes.Detail(postId))
                })
            }
            entry<Routes.Detail> { key ->
                PostDetailScreen(postId = key.id)
            }
        }
    )
}