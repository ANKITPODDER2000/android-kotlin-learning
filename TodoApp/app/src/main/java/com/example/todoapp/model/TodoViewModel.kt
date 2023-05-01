package com.example.todoapp.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.Date

class TodoViewModel(context: Context) : ViewModel() {
    private val db = TodoDataBase.getInstance(context)
    private val dbDao = db.getTodoDao()
    private var _todos: LiveData<List<Todo>> = dbDao.getAllTodo()
    val todos
        get() = _todos


    suspend fun addNewTodo(todo: Todo) {
        dbDao.upsertTodo(todo)
    }
    suspend fun deleteTodo(todo: Todo) {
        dbDao.deleteTodo(todo)
    }
}