package com.example.pdm_00097524.RankeUca.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.pdm_00097524.RankeUca.data.local.database.entities.OptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {

    @Query("SELECT * FROM OPTIONS WHERE questionId = :questionId")
    fun getAllOptions(questionId: Int): Flow<List<OptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: OptionEntity)

    @Delete
    suspend fun deleteOption(option: OptionEntity)

    @Update
    suspend fun updateOption(option: OptionEntity)


    @Upsert
    suspend fun upsertOption(option: OptionEntity)

    @Upsert
    suspend fun upsertOptions(options: List<OptionEntity>)

    @Query("DELETE FROM options WHERE id = :id")
    suspend fun deleteOptionnById(id: Int)

    @Query("""
    UPDATE options 
    SET value = COALESCE(:title, value),
        imageUrl = COALESCE(:imageUrl, imageUrl),
        questionId = COALESCE(:questionId, questionId)
    WHERE id = :id
    """)
    suspend fun updateOption(id: Int,
                             title: String? = null,
                             imageUrl: String? = null,
                             questionId: Int? = null)
}