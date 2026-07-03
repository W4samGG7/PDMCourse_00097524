package com.example.pdm_00097524.RankeUca.data.repositories.repositoryAuth

import com.example.pdm_00097524.RankeUca.data.remote.api.Auth.RegisterRequestDto
import com.example.pdm_00097524.RankeUca.data.remote.api.Auth.RegisterResponseDto
import com.example.pdm_00097524.RankeUca.data.remote.api.KtorClient
import com.example.pdm_00097524.RankeUca.data.session.SessionManager
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl (
    private val session: SessionManager
) : AuthRepository {
    override val isLoggedIn: Flow<Boolean> = session.apikey.map { it != null }
    override val userName: Flow<String?> = session.name
    override val userCarnet: Flow<String?> = session.carnet

    override suspend fun register(name: String, carnet: String) : Boolean {
       return try {

        val response: RegisterResponseDto = KtorClient.client.post("register"){
            contentType(ContentType.Application.Json)
            setBody(RegisterRequestDto(carnet = carnet))
        }.body()

        if(response.ok && response.apiKey != null){
            session.save(apikey = response.apiKey, name = name, carnet = carnet)
            KtorClient.authApiKey = response.apiKey
            true
        }else{
            false
        }

    } catch (e: Exception){
        false
    }
    }

    override suspend fun logout() {
        session.clear()
        KtorClient.authApiKey = null
    }
}