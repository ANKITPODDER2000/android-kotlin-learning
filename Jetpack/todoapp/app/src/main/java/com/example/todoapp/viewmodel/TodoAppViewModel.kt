package com.example.todoapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todoapp.db.TodoDao
import com.example.todoapp.db.TodoDataBase
import com.example.todoapp.module.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAppViewModel @Inject constructor(
    private val todoDao: TodoDao
) : ViewModel() {
    private val _allTodo = MutableStateFlow(emptyList<Todo>())
    val allTodo: StateFlow<List<Todo>>
        get() = _allTodo.asStateFlow()

    fun getAllTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("DEBUG_ANKIT", "getAllTodo: is called")
            todoDao.getAllTodo().collect { it ->
                _allTodo.value = it
                Log.d("DEBUG_ANKIT", "getAllTodo: size -> ${_allTodo.value.size}")
            }
        }
    }

}