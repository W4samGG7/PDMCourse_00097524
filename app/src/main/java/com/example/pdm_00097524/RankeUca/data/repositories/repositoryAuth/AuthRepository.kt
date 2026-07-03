package com.example.pdm_00097524.RankeUca.data.repositories.repositoryAuth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    val userName: Flow<String?>

    val userCarnet: Flow<String?>
    suspend fun register(name: String, carnet: String) : Boolean
    suspend fun logout()
}