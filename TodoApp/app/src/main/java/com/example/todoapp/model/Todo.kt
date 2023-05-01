package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo(
    var todoTitle: String,
    var todoDescription: String,
    var startDate: String,
    var endData: String,
    var status: TodoStatus,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
): Serializable
