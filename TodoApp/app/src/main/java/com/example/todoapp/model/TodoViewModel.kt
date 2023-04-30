package com.example.todoapp.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class TodoViewModel(context: Context) : ViewModel() {
    fun addNewTodo(todo: Todo) {

    }

    //    private val db: TodoDataBase = TodoDataBase.getInstance(context)
//    private val dbDao = db.getTodoDao()
//    var todos: LiveData<List<Todo>> = dbDao.getAllTodo()
    var todos: MutableLiveData<List<Todo>> = MutableLiveData<List<Todo>>(
        listOf(
            Todo("Learn DSA", "Complete Tree", Date(), Date().toString(), TodoStatus.IN_PROGRESS),
            Todo("Learn DSA", "Complete Tree", Date(), Date().toString(), TodoStatus.IN_PROGRESS)
        )
    )

//    suspend fun addNewTodo(todo: Todo) {
//        dbDao.upsertTodo(todo)
//    }
//
//    suspend fun deleteTodo(todo: Todo) {
//        dbDao.deleteTodo(todo)
//    }
}