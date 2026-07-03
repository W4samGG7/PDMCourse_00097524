package com.example.pdm_00097524.RankeUca.data.remote.api.Auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val carnet: String
)

@Serializable
data class RegisterResponseDto(
    val ok: Boolean,
    val apiKey: String? = null,
    val message: String? = null
)