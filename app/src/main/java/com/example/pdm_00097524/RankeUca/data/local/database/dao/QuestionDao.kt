package com.example.pdm_00097524.RankeUca.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.example.pdm_00097524.RankeUca.data.local.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.local.database.entities.QuestionwithOptions
import com.example.pdm_00097524.RankeUca.data.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao{

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionsWithOptions(): Flow<List<QuestionwithOptions>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)
    @Delete
    suspend fun deleteQuestion(question: QuestionEntity)

    @Update
    suspend fun updateQuestion(question: QuestionEntity)

    //(Refresh) API->Room
    @Upsert
    suspend fun upsertQuestion(question: QuestionEntity)

    @Upsert
    suspend fun upsertQuestions(questions: List<QuestionEntity>)

    @Query("DELETE FROM questions WHERE id = :id")
    suspend fun deleteQuestionById(id: Int)

    @Query("UPDATE questions SET title = :title where id = :id")
    suspend fun updateQuestion(id: Int, title: String)
}