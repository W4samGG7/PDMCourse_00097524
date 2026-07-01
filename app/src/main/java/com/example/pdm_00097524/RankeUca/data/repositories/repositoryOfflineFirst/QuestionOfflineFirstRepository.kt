package com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst

import com.example.pdm_00097524.RankeUca.data.model.Option
import com.example.pdm_00097524.RankeUca.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionOfflineFirstRepository {

    fun getQuestions(): Flow<List<Question>>
    fun getOptions(questionId: Int): Flow<List<Option>>

    suspend fun refresh() : Result<Unit>

    suspend fun refreshQuestionOptions(questionId: Int): Result<Unit>

    suspend fun createQuestion(title: String): Result<Unit>
    suspend fun updateQuestion(id: Int, title: String): Result<Unit>
    suspend fun deleteQuestion(id: Int): Result<Unit>

    suspend fun createOption(questionId: Int, value: String, imageUrl: String?): Result<Unit>
    suspend fun updateOption(id: Int, value: String?,imageUrl: String?, questionId: Int? ): Result<Unit>
    suspend fun deleteOption(id: Int): Result<Unit>

}