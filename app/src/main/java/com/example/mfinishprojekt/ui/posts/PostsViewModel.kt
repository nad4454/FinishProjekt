package com.example.mfinishprojekt.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mfinishprojekt.db.DbConnection
import com.example.mfinishprojekt.db.PostEntityDao
import com.example.mfinishprojekt.ui.posts.restapi.PostService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsViewModel(application: Application) : AndroidViewModel(application) {


    private val postService: PostService
    private val postEntityDao: PostEntityDao
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        postService = retrofit.create(PostService::class.java)

        postEntityDao = DbConnection.getDatabase(application.applicationContext).dao();
    }

    private val postsRepository = PostsRepository(postService, postEntityDao)



    var posts: MutableLiveData<List<Post>?> = MutableLiveData()
    init {
        viewModelScope.launch {
            postsRepository.getAll().collectLatest {
                posts.value = it
            }
        }
    }

    fun saveInCache(){
        viewModelScope.launch {
            posts.value?.let { postEntityDao.insert(it) }
        }
    }



}