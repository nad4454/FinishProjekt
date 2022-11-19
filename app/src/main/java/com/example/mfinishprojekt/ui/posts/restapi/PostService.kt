package com.example.mfinishprojekt.ui.posts.restapi

import com.example.mfinishprojekt.ui.posts.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts/")
    fun getAll(): Call<List<Post>>
}