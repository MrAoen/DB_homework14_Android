package com.example.db_homework14_android.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.db_homework14_android.API_KEY
import com.example.db_homework14_android.data.VideoRoom
import com.example.db_homework14_android.repository.DatabaseConfig
import com.example.db_homework14_android.repository.RemoteApi
import com.example.db_homework14_android.repository.RemoteConfig
import com.example.db_homework14_android.repository.VideoDao
import kotlinx.coroutines.launch

class VideoModelView(application: Application) : AndroidViewModel(application) {

    var listItem = MutableLiveData<List<VideoRoom>>(emptyList())
    private var videoDao: VideoDao? = null

    fun getVideoList(year: String) {
        viewModelScope.launch {
            videoDao = DatabaseConfig.getInstance(getApplication())
            var list = videoDao?.selectByYear(year)
            if (list == null) {
                list = emptyList()
            }
            if (list.isEmpty()) {
                val obj = RemoteConfig()
                    .getClient()
                    .create(RemoteApi::class.java)
                    .getYearMovies(API_KEY, year)
                obj.body().apply {
                    this?.data?.forEach {
                        val newVideo = VideoRoom(
                            it?.overview ?: "no overviewed yet",
                            it?.poster_path ?: "",
                            it?.release_date?.subSequence(0,4).toString(),
                            it?.title ?: "no title",
                            it?.vote_average ?: 0.0,
                            it?.id ?: 0
                        )
                        list.plus(newVideo)
                        updateOrInsert(newVideo)
                    }
                }
            }
            listItem.value = list
        }
    }

    private suspend fun updateOrInsert(newVideo: VideoRoom) {
        val db = DatabaseConfig.getInstance(getApplication())
        if (db.ifExist(newVideo.video_id) != null) {
            db.update(newVideo)
        } else {
            db.insert(newVideo)
        }
    }
}