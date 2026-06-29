package com.example.pdm_00097524.RankeUca.data.model


import com.example.pdm_00097524.RankeUca.data.local.database.entities.OptionEntity

data class Option(
    val id: Int = 0,
    val value: String,
    val imageUrl: String? = null,
    val votes: Int,
    val questionId: Int = 0,
)

fun Option.toEntity(): OptionEntity {
    return OptionEntity(
        id = id,
        value = value,
        imageUrl = imageUrl,
        votes = votes,
        questionId = questionId
    )
}