package com.example.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.module.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDataBase: RoomDatabase() {
    abstract fun getDao() : TodoDao
}
