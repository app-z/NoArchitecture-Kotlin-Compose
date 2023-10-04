package com.example.composegenapp.remote

import com.example.composegenapp.common.Constants.Companion.LAUNCHES_URL
import com.example.composegenapp.data.FalconInfoResult
import de.jensklingenberg.ktorfit.http.GET

interface ApiService {
    @GET(LAUNCHES_URL)
    suspend fun getFalconInfo(): List<FalconInfoResult>
}
