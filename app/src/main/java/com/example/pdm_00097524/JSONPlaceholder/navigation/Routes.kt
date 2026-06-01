package com.example.pdm_00097524.JSONPlaceholder.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey{

    @Serializable
    data object Home : Routes()

    @Serializable
    data class Detail(val id: Int) : Routes()
}