package com.example.db_homework14_android.repository

import androidx.lifecycle.MutableLiveData
import androidx.room.*

import com.example.db_homework14_android.data.VideoRoom

@Dao
interface VideoDao {

    @Insert
    suspend fun insert(video:VideoRoom)

    @Delete
    suspend fun delete(video: VideoRoom)

    @Update
    suspend fun update(video: VideoRoom)

    @Query("select * from video v where v.release_date=:year")
    suspend fun selectByYear(year: String): List<VideoRoom>

    @Query("select v.id from video v where v.video_id=:id")
    suspend fun ifExist(id:Int):Int?
}