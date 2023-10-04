package com.example.composegenapp.remote

import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.data.FalconInfoResult

interface RemoteDataSource {
    suspend fun getFalconInfo(): ResponseResult<List<FalconInfoResult>>
}
