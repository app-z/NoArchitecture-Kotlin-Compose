package com.example.composegenapp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class SpaceX {

    companion object {
        const val url = "https://api.spacexdata.com/v4/launches"
    }

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

//    @Throws(Exception::class)
    suspend fun getRockets(): List<FalconInfo> {
        val rockets: Array<FalconInfo> =
            httpClient.get(url).body()
        return rockets.asList()
    }
}

//    LaunchedEffect(keyLoad) {
//        textError = "Ok"
//        visibleProgress = true
////        rockets = null
//        visibleError = try {
//            scope.run {
//                rockets = SpaceX().getRockets()
//                false
//            }
//        } catch (e: Exception) {
//            textError = e.localizedMessage ?: "error"
//            true
//        }
//    }

