package com.example.pdm_00097524.RankeUca.data.repository

import com.example.pdm_00097524.RankeUca.data.database.dao.QuestionDao
import com.example.pdm_00097524.RankeUca.data.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.database.entities.toModel
import com.example.pdm_00097524.RankeUca.model.Question
import com.example.pdm_00097524.RankeUca.model.toEntity
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
}