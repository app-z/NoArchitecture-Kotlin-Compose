package com.example.composegenapp.domain.domain.repository

import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onFlowStarts
import com.example.composegenapp.data.DataMapper
import com.example.composegenapp.domain.domain.model.FalconInfo
import com.example.composegenapp.remote.RemoteDataSource
import com.galeryalina.domain.repository.FalconRepository
import com.galeryalina.local.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FalconRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : FalconRepository {
    override suspend fun getFalconInfo(): Flow<ResponseResult<List<FalconInfo>>> {
        return flow  {
            if (localDataSource.getAllRocketsInfo().isNotEmpty()) {
                emit(ResponseResult.Success(
                    DataMapper.entryToDomain(localDataSource.getAllRocketsInfo())))
            } else {
                remoteDataSource.getFalconInfo().run {
                    when (this) {
                        is ResponseResult.Success -> {
                            localDataSource
                                .insertAllRocketsInfo(
                                    DataMapper.responseToEntry(response))
                            emit(ResponseResult.Success(DataMapper.responseToDomain(response)))
                        }
                        is ResponseResult.Error -> {
                            emit(ResponseResult.Error(exception))
                        }
                        else -> {}
                    }
                }
            }
        }.flowOn(Dispatchers.IO).onFlowStarts()
    }

}
