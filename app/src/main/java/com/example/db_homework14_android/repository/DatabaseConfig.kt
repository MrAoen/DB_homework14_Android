package com.example.db_homework14_android.repository

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.db_homework14_android.data.VideoRoom
import java.util.concurrent.Executors

@Database(entities = [VideoRoom::class],version = 1)
abstract class DatabaseConfig: RoomDatabase() {
    abstract fun VideoDao() : VideoDao

    companion object {

        private var INSTANCE: VideoDao? = null

        fun getInstance(context: Context): VideoDao {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseConfig::class.java,
                        "video_database_v1"
                    ).setQueryCallback(RoomDatabase.QueryCallback { sqlQuery, bindArgs ->
                        Log.i("MY-SQL","SQL Query: $sqlQuery SQL Args: $bindArgs")
                    }, Executors.newSingleThreadExecutor())
                        .fallbackToDestructiveMigration()
                        .build().VideoDao()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}