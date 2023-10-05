package com.example.composegenapp.domain.repository

import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.domain.model.FalconInfo
import kotlinx.coroutines.flow.Flow

interface FalconRepository {
    fun getFalconInfo(): Flow<ResponseResult<List<FalconInfo>>>
}
