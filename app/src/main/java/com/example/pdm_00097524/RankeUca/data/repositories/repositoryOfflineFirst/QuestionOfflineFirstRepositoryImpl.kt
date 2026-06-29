package com.example.pdm_00097524.RankeUca.data.repositories.repositoryOfflineFirst

import com.example.pdm_00097524.RankeUca.data.local.database.dao.OptionDao
import com.example.pdm_00097524.RankeUca.data.local.database.dao.QuestionDao
import com.example.pdm_00097524.RankeUca.data.local.database.entities.QuestionEntity
import com.example.pdm_00097524.RankeUca.data.local.database.entities.toModel
import com.example.pdm_00097524.RankeUca.data.model.Option
import com.example.pdm_00097524.RankeUca.data.model.Question
import com.example.pdm_00097524.RankeUca.data.model.toEntity
import com.example.pdm_00097524.RankeUca.data.remote.api.KtorClient
import com.example.pdm_00097524.RankeUca.data.remote.api.Option.CreateOption
import com.example.pdm_00097524.RankeUca.data.remote.api.Option.OptionDTO
import com.example.pdm_00097524.RankeUca.data.remote.api.Option.toEntity
import com.example.pdm_00097524.RankeUca.data.remote.api.Option.toModel
import com.example.pdm_00097524.RankeUca.data.remote.api.Question.QuestionDTO
import com.example.pdm_00097524.RankeUca.data.remote.api.Question.toEntity
import com.example.pdm_00097524.RankeUca.data.remote.api.Question.toModel
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionOfflineFirstRepositoryImpl (
    private val questionDao: QuestionDao,
    private val optionDao: OptionDao
) : QuestionOfflineFirstRepository {

    // Leer: de Room (fuente de verdad)
    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithOptions().map { list->
            list.map { it.toModel() }
        }
    }

    override fun getOptions(questionId: Int): Flow<List<Option>> {
        return optionDao.getAllOptions(questionId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    // Sincronizar: API -> Room
    override suspend fun refresh(): Result<Unit> {
        try {

            val questions: List<QuestionDTO> = KtorClient.client.get("questions").body()
            val options: List<OptionDTO> = KtorClient.client.get("options").body()

            questionDao.upsertQuestions(questions.map { optionDTO-> optionDTO.toEntity() })
            optionDao.upsertOptions(options.map { optionDTO -> optionDTO.toEntity() })

            return Result.success(Unit)
        } catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun refreshQuestionOptions(questionId: Int): Result<Unit> {
        try {
            val response: List<OptionDTO> = KtorClient.client.get("questions/$questionId/options").body()

            optionDao.upsertOptions(response.map { optionDTO -> optionDTO.toEntity() })

            return Result.success(Unit)
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    // Mutar: API -> luego refresh()
    override suspend fun createQuestion(title: String): Result<Question> {
        try {
            val response: QuestionDTO = KtorClient.client.post("questions"){
                contentType(ContentType.Application.Json)
                setBody(mapOf("title" to title))
            }.body()

            questionDao.upsertQuestion(response.toEntity())

            return Result.success(response.toModel())
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun updateQuestion(id: Int, title: String): Result<Unit> {
        try {
            KtorClient.client.put("questions/$id") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("title" to title))
            }

            questionDao.updateQuestion(id = id,title = title)
            return Result.success(Unit)
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun deleteQuestion(id: Int): Result<Unit> {
        try {
            KtorClient.client.delete("questions/$id")

            questionDao.deleteQuestionById(id)

            return Result.success(Unit)
        } catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun createOption(questionId: Int, value: String, imageUrl: String?): Result<Option>{
        try {

            val response: OptionDTO = KtorClient.client.post("options"){
                contentType(ContentType.Application.Json)
                setBody(
                    CreateOption(
                        name = value,
                        questionId = questionId,
                        imageUrl = imageUrl
                    )
                )}.body()

            optionDao.upsertOption(response.toEntity())

            return Result.success(response.toModel())

        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun updateOption(
        id: Int,
        value: String?,
        imageUrl: String?,
        questionId: Int?
    ): Result<Unit> {
        try {
            val body = mutableMapOf<String, Any>()
            if (value != null) body["name"] = value
            if (imageUrl != null) body["imageUrl"] = imageUrl
            if (questionId != null) body["questionId"] = questionId

            if (body.isNotEmpty()){
                KtorClient.client.put("options/$id") {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                }

                optionDao.updateOption(
                    id = id,
                    title = value,
                    imageUrl = imageUrl,
                    questionId = questionId
                )
            }

            return Result.success(Unit)
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun deleteOption(id: Int): Result<Unit> {
        try {

            KtorClient.client.delete("options/$id")

            optionDao.deleteOptionnById(id)

            return Result.success(Unit)

        }catch (e: Exception){
            return Result.failure(e)
        }
    }


}