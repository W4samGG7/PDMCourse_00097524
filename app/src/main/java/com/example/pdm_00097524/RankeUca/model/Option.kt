package com.example.pdm_00097524.RankeUca.model


import com.example.pdm_00097524.RankeUca.data.database.entities.OptionEntity

data class Option(
    val id: Int = 0,
    val value: String,
    val imageUrl: String? = null,
    val questionId: Int = 0
)

fun Option.toEntity(): OptionEntity {
    return OptionEntity(
        id = id,
        value = value,
        imageUrl = imageUrl,
        questionId = questionId
    )
}