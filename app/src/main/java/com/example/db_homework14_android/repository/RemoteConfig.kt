package com.example.db_homework14_android.repository

import com.example.db_homework14_android.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RemoteConfig {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}