package com.example.pdm_00097524.RankeUca.data.remote.api.Option

import kotlinx.serialization.Serializable

@Serializable
data class CreateOption(
    val name: String,
    val questionId: Int,
    val imageUrl: String? = null
)