package com.example.mfinishprojekt.ui.albums

import retrofit2.Call
import retrofit2.http.GET

interface PhotoApi {

    @GET("photos/")
    fun getAll(): Call<List<Photo>>
}