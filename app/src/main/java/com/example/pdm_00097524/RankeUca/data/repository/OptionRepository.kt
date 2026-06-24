package com.example.pdm_00097524.RankeUca.data.repository

import com.example.pdm_00097524.RankeUca.model.Option
import kotlinx.coroutines.flow.Flow

interface OptionRepository {
    fun getOptions(questionId: Int): Flow<List<Option>>
    suspend fun addOption(name: String, imageUrl: String, questionId: Int)
    suspend fun deleteOption(option: Option)

    suspend fun updateOption(option: Option)
}