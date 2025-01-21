package com.example.hellojetpackcompose.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object client {

    private const val komryuu = "https://www.komryuu.my.id/"

    val komryuget: Apiservice by lazy {
        Retrofit.Builder()
            .baseUrl(komryuu)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiservice::class.java)
    }
}