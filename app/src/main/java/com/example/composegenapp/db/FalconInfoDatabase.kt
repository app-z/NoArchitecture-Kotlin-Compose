package com.example.composegenapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [FalconInfoEntity::class], version = 1, exportSchema = false)
abstract class FalconInfoDatabase : RoomDatabase() {
    abstract fun dao(): FalconInfoDao

    companion object {
        @Volatile
        private var INSTANCE: FalconInfoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FalconInfoDatabase = buildDatabase(context, scope)

        private fun buildDatabase(appContext: Context, scope: CoroutineScope): FalconInfoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext, FalconInfoDatabase::class.java, "FalconsDatabase.db"
                ).fallbackToDestructiveMigration().addCallback(ItemCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class ItemCallback(val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                INSTANCE?.let {
                    scope.launch {
                        //it.dao().insertAllRocketsInfo(ConverterData.toEntry(ConverterData.getRockets()))
                    }
                }
            }

            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
                INSTANCE?.let {
                    scope.launch {
                        it.dao().cleanRockets()
                        //it.dao().insertAllRocketsInfo(ConverterData.toEntry(ConverterData.getRockets()))
                    }
                }
            }
        }
    }
}
