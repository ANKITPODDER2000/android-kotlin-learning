package com.example.todoapp.model

import androidx.lifecycle.ViewModel
import java.util.Date

class NewTodoViewModel: ViewModel() {
    var todoTitle = ""
    var todoDescription = ""
    var todoStartDate = Date()
    var todoDueDate = Date().toString()
    var todoStatus = TodoStatus.NOT_STARTED

    fun resetAllItems() {
        todoTitle = ""
        todoDescription = ""
        todoStartDate = Date()
        todoDueDate = Date().toString()
        todoStatus = TodoStatus.NOT_STARTED
    }

    fun isValid(): Boolean{
        return !(todoTitle.isEmpty() || todoDescription.isEmpty())
    }
}