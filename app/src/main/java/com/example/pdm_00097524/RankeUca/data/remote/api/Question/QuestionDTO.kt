package com.example.pdm_00097524.RankeUca.data.remote.api.Question

import com.example.pdm_00097524.RankeUca.data.local.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.model.Question
import kotlinx.serialization.Serializable

@Serializable
data class QuestionDTO(
    val id: Int,
    val title: String
)

fun QuestionDTO.toModel(): Question {
    return Question(
        id = id,
        title = title,
        optionCount = 0
    )
}

fun QuestionDTO.toEntity(): QuestionEntity {
    return QuestionEntity(
        id = id,
        title = title
    )
}