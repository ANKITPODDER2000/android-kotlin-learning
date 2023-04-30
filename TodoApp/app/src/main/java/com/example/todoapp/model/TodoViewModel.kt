package com.example.todoapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class TodoViewModel : ViewModel() {
    private var _todos: MutableLiveData<List<Todo>> = MutableLiveData<List<Todo>>(
        listOf(
            Todo("Learn DSA", "Complete Tree", Date(), Date().toString(), TodoStatus.IN_PROGRESS),
            Todo("Learn DSA", "Complete Tree", Date(), Date().toString(), TodoStatus.IN_PROGRESS)
        )
    )
    val todos
        get() = _todos

    fun addNewTodo(todo: Todo) {
        _todos.value = _todos.value?.plus(todo)
    }
}