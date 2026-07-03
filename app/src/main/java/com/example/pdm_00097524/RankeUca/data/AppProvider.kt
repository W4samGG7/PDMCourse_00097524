package com.example.pdm_00097524.RankeUca.data

import android.content.Context
import com.example.pdm_00097524.RankeUca.data.local.database.AppDataBase
import com.example.pdm_00097524.RankeUca.data.remote.api.KtorClient
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryAuth.AuthRepository
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryAuth.AuthRepositoryImpl
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst.QuestionOfflineFirstRepository
import com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst.QuestionOfflineFirstRepositoryImpl
import com.example.pdm_00097524.RankeUca.data.session.SessionManager
import kotlinx.coroutines.flow.first

class AppProvider(context: Context){
    private val appDataBase = AppDataBase.getDatabase(context)
    private val questionDao = appDataBase.questionDao()
    private val optionDao = appDataBase.optionDao()

    private val sessionManager = SessionManager(context)

    suspend fun loadSavedApiKey(){
        val savedKey = sessionManager.apikey.first()
        if (savedKey != null){
            KtorClient.authApiKey = savedKey
        }
    }

    private val questionOfflineFirstRepository: QuestionOfflineFirstRepository =
        QuestionOfflineFirstRepositoryImpl(questionDao,optionDao)

    private val authRepository: AuthRepository = AuthRepositoryImpl(sessionManager)

    fun provideQuestionOfflineFirstRepository(): QuestionOfflineFirstRepository {
        return questionOfflineFirstRepository
    }

    fun provideAuthRepository(): AuthRepository {
        return authRepository
    }
}