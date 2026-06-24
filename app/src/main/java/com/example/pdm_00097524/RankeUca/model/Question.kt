package com.example.pdm_00097524.RankeUca.model

import com.example.pdm_00097524.RankeUca.data.database.entities.QuestionEntity

data class Question(
    val id: Int = 0,
    val title: String,
    val optionCount: Int = 0
)

fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        id = id,
        title = title
    )
}