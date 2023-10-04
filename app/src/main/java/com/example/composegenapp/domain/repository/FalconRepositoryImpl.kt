package com.example.composegenapp.domain.repository

import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onFlowStarts
import com.example.composegenapp.data.DataMapper
import com.example.composegenapp.domain.model.FalconInfo
import com.example.composegenapp.local.LocalDataSource
import com.example.composegenapp.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FalconRepositoryImpl @Inject constructor(
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
                            emit(ResponseResult.Success(DataMapper.responseToDomain(response)))
                            localDataSource
                                .insertAllRocketsInfo(
                                    DataMapper.responseToEntry(response))
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
