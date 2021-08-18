package com.example.db_homework14_android.repository

import com.example.db_homework14_android.ENDPOINT1
import com.example.db_homework14_android.data.VideoRemote
import com.fasterxml.jackson.annotation.JsonProperty
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteApi {

    @GET(ENDPOINT1)
    suspend fun getYearMovies(
        @Query("api_key") apiKey: String, @Query("primary_release_year") year: String
    ):Response<SearchResult>

}

data class SearchResult(
    @JsonProperty("results")
    val `data`: List<VideoRemote?>,//List<VideoRemote>,
    @JsonProperty("page")
    val meta: String,
    @JsonProperty("total_pages")
    val total_pages: String,
    @JsonProperty("total_results")
    val total_results:String
)