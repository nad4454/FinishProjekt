package com.example.mfinishprojekt.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mfinishprojekt.ui.albums.models.PhotoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumsViewModel : ViewModel() {

    private val photoApi: PhotoApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        photoApi = retrofit.create(PhotoApi::class.java)
    }


    private val photoRepository = PhotoRepository(photoApi)

    val photos: MutableLiveData<List<Photo>?> = photoRepository.getAll()


}