package com.example.pdm_00097524.RankeUca.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pdm_00097524.RankeUca.data.database.entities.OptionEntity
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
}