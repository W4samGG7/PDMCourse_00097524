package com.example.pdm_00097524.RankeUca.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pdm_00097524.RankeUca.data.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.database.entities.QuestionwithOptions
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

}