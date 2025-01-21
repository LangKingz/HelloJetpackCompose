package com.example.hellojetpackcompose.data

import com.example.hellojetpackcompose.data.response.Bookmark
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Apiservice {
    @GET("api/bookmark")
    suspend fun getBookmark(@Query("email") email:String = "gilanglibna@gmail.com",@Header("Authorization") token:String = "lang"):Bookmark

}