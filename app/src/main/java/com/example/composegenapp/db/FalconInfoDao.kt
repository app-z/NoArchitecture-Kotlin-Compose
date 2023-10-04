package com.example.composegenapp.db

import androidx.room.*
import java.util.*

@Dao
interface FalconInfoDao {

    @Query("SELECT * FROM FalconInfo")
    fun getAllRocketsInfo(): List<FalconInfoEntity>

    @Query("SELECT * FROM FalconInfo WHERE id = :id")
    fun getRocketsInfo(id: Int): List<FalconInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRocketsInfo(falconInfoResult: List<FalconInfoEntity>)

    @Query("DELETE FROM FalconInfo")
    fun cleanRockets()
}
