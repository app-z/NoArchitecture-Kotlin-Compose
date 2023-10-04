package com.example.composegenapp.local

import com.example.composegenapp.db.FalconInfoDao
import com.example.composegenapp.db.FalconInfoEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val falconInfoDao: FalconInfoDao) {

    fun getAllRocketsInfo(): List<FalconInfoEntity> = falconInfoDao.getAllRocketsInfo()

    fun insertAllRocketsInfo(falconInfoEntityList :List<FalconInfoEntity>) = falconInfoDao.insertAllRocketsInfo(falconInfoEntityList)
}
