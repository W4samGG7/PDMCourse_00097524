package com.example.pdm_00097524.RankeUca.data

import android.content.Context
import com.example.pdm_00097524.RankeUca.data.database.AppDataBase
import com.example.pdm_00097524.RankeUca.data.repository.OptionRepository
import com.example.pdm_00097524.RankeUca.data.repository.OptionRespositoryImpl
import com.example.pdm_00097524.RankeUca.data.repository.QuestionRepository
import com.example.pdm_00097524.RankeUca.data.repository.QuestionRepositoryImpl

class AppProvider(context: Context){
    private val appDataBase = AppDataBase.getDatabase(context)
    private val questionDao = appDataBase.questionDao()
    private val optionDao = appDataBase.optionDao()

    private val questionRepository: QuestionRepository =
        QuestionRepositoryImpl(questionDao)
    private val optionRepository: OptionRepository =
        OptionRespositoryImpl(optionDao)

    fun provideQuestionRepository(): QuestionRepository{
        return questionRepository
    }
    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }
}