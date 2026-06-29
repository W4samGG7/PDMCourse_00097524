package com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom

import com.example.pdm_00097524.RankeUca.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(): Flow<List<Question>>
    suspend fun addQuestion(title: String)
    suspend fun deleteQuestion(question: Question)

    suspend fun updateQuestion(question: Question)
}