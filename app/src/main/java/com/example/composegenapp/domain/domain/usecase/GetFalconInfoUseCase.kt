package com.example.composegenapp.domain.domain.usecase

import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.domain.domain.model.FalconInfo
import kotlinx.coroutines.flow.Flow

interface GetFalconInfoUseCase {
    suspend fun getFalconInfo(): Flow<ResponseResult<List<FalconInfo>>>
}
