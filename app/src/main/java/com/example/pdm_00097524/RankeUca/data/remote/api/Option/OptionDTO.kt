package com.example.pdm_00097524.RankeUca.data.remote.api.Option

import com.example.pdm_00097524.RankeUca.data.local.database.entities.OptionEntity
import com.example.pdm_00097524.RankeUca.data.local.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.model.Option
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class OptionDTO(
    val id: Int,
    @SerialName("name")val value: String,
    val imageUrl: String,
    val votes: Int,
    val questionId: Int
)

fun OptionDTO.toModel(): Option {
    return Option(
        id = id,
        value = value,
        imageUrl = imageUrl,
        votes = votes,
        questionId = questionId
    )
}

fun OptionDTO.toEntity(): OptionEntity {
    return OptionEntity(
        id = id,
        value = value,
        imageUrl = imageUrl,
        votes = votes,
        questionId = questionId
    )
}
