package com.galeryalina.local

import com.example.composegenapp.db.FalconInfoDao
import com.example.composegenapp.db.FalconInfoEntity

class LocalDataSource constructor(private val falconInfoDao: FalconInfoDao) {

    fun getAllRocketsInfo(): List<FalconInfoEntity> = falconInfoDao.getAllRocketsInfo()

    fun insertAllRocketsInfo(falconInfoEntityList :List<FalconInfoEntity>) = falconInfoDao.insertAllRocketsInfo(falconInfoEntityList)
}
