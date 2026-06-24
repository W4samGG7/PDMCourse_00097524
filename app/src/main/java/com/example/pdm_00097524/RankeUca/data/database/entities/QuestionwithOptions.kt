package com.example.pdm_00097524.RankeUca.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pdm_00097524.RankeUca.model.Question

data class QuestionwithOptions(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val options: List<OptionEntity>
)

fun QuestionwithOptions.toModel(): Question{
    return Question(
        id = question.id,
        title = question.title,
        optionCount = options.size
    )

}