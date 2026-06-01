package com.example.pdm_00097524.JSONPlaceholder.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val client = HttpClient(OkHttp) {
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }

        install(Logging){
            level = LogLevel.ALL
        }

        defaultRequest {
            url(BASE_URL)
            header(HttpHeaders.Accept,"application/json")
        }
    }
}