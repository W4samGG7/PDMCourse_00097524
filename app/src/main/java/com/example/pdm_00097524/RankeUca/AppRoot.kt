package com.example.pdm_00097524.RankeUca

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdm_00097524.RankeUca.ui.screens.auth.AuthViewModel
import androidx.compose.runtime.getValue
import com.example.pdm_00097524.RankeUca.navigation.RankeUCA_App
import com.example.pdm_00097524.RankeUca.ui.screens.auth.LoginScreen
import com.example.pdm_00097524.RankeUca.ui.screens.splash.SplashScreen

@Composable
fun AppRoot(
    authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
){
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    when(isLoggedIn){
        null -> SplashScreen()
        false -> LoginScreen()
        true -> RankeUCA_App(
            userName = authViewModel.userName.collectAsState().value,
            onLogout = {authViewModel.logout()}
        )
    }
}