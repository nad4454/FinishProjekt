package com.example.mfinishprojekt.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mfinishprojekt.ui.posts.Post

@Database(entities = [Post::class], version = 1)
abstract class DbConnection : RoomDatabase() {
    abstract fun dao(): PostEntityDao

    companion object {
        private var INSTANCE: DbConnection? = null
        fun getDatabase(context: Context): DbConnection {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,DbConnection::class.java, "post_database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}