package com.example.composegenapp.data

import com.example.composegenapp.common.Constants.Companion.rocketsUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class SpaceX {

    private val httpClient = HttpClient {
        install(HttpCache)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    suspend fun getRockets(): List<FalconInfo> {
        val rockets: Array<FalconInfo> =
            httpClient.get(rocketsUrl).body()
        return rockets.asList()
    }
}
