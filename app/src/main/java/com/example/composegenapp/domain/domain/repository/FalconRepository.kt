package com.galeryalina.domain.repository

import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.domain.domain.model.FalconInfo
import kotlinx.coroutines.flow.Flow

interface FalconRepository {
    suspend fun getFalconInfo(): Flow<ResponseResult<List<FalconInfo>>>
}
