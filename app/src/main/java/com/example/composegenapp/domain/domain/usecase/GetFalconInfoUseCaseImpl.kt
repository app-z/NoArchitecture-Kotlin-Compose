package com.example.composegenapp.domain.domain.usecase

import com.example.composegenapp.domain.domain.repository.FalconRepository
import javax.inject.Inject

class GetFalconInfoUseCaseImpl @Inject constructor(private val falconInfoRepository: FalconRepository)
    : GetFalconInfoUseCase{
    override suspend fun getFalconInfo() = falconInfoRepository.getFalconInfo()
}
