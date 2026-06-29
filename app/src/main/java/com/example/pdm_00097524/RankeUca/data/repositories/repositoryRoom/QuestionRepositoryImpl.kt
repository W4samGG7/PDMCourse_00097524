package com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom

import com.example.pdm_00097524.RankeUca.data.local.database.dao.QuestionDao
import com.example.pdm_00097524.RankeUca.data.local.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.local.database.entities.toModel
import com.example.pdm_00097524.RankeUca.data.model.Question
import com.example.pdm_00097524.RankeUca.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepositoryImpl (
    private val questionDao: QuestionDao
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithOptions().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun addQuestion(title: String) {
        questionDao.insertQuestion(QuestionEntity(title = title))
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question.toEntity())
    }

    override suspend fun updateQuestion(question: Question) {
        questionDao.updateQuestion(question.toEntity())
    }
}