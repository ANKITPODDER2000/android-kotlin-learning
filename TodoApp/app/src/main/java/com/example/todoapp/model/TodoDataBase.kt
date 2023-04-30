package com.example.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
    companion object{
        private var db: TodoDataBase? = null
        fun getInstance(context: Context): TodoDataBase {
            @Volatile
            db = db ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context, TodoDataBase::class.java, "todoDB"
                    ).build()
            }
            return db as TodoDataBase
        }
    }
}