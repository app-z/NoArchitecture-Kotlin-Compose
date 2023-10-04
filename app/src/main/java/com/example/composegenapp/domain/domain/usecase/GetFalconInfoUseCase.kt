package com.example.composegenapp.domain.domain.usecase

import com.example.composegenapp.domain.domain.repository.FalconRepositoryImpl

class GetFalconInfoUseCase constructor(private val falconInfoRepository: FalconRepositoryImpl) {
    suspend fun getFalconInfo() = falconInfoRepository.getFalconInfo()
}
