//package com.example.composegenapp.data
//
//import com.example.composegenapp.common.Constants.Companion.LAUNCHES_URL
//import com.example.composegenapp.common.ResponseResult
//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.plugins.cache.HttpCache
//import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
//import io.ktor.client.request.get
//import io.ktor.serialization.kotlinx.json.json
//import kotlinx.serialization.json.Json
//
//
//class SpaceX {
//
//    private val httpClient = HttpClient {
//        install(HttpCache)
//        install(ContentNegotiation) {
//            json(Json {
//                prettyPrint = true
//                isLenient = true
//                ignoreUnknownKeys = true
//            })
//        }
//    }
//
//    @Throws(Exception::class)
//    suspend fun getRockets(): ResponseResult<List<FalconInfoResult>> {
//        return try {
//            val rockets: Array<FalconInfoResult> = httpClient.get(LAUNCHES_URL).body()
//            ResponseResult.Success(rockets.asList())
//        } catch (e: Exception) {
//            e.message?.let { ResponseResult.Error(it) }
//                .run { ResponseResult.Error("Unexpected error" )}
//        }
//    }
//}
