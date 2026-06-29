package com.example.pdm_00097524.RankeUca.data

import android.content.Context
import com.example.pdm_00097524.RankeUca.data.local.database.AppDataBase
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst.QuestionOfflineFirstRepository
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst.QuestionOfflineFirstRepositoryImpl
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom.OptionRepository
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom.OptionRespositoryImpl
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom.QuestionRepository
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryRoom.QuestionRepositoryImpl

class AppProvider(context: Context){
    private val appDataBase = AppDataBase.getDatabase(context)
    private val questionDao = appDataBase.questionDao()
    private val optionDao = appDataBase.optionDao()

    private val questionOfflineFirstRepository: QuestionOfflineFirstRepository =
        QuestionOfflineFirstRepositoryImpl(questionDao,optionDao)

    fun provideQuestionOfflineFirstRepository(): QuestionOfflineFirstRepository {
        return questionOfflineFirstRepository
    }
}