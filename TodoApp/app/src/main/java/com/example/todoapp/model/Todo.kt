package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Todo")
data class Todo(
    var todoTitle: String,
    var todoDescription: String,
    var startDate: Date,
    var endData: String,
    var status: TodoStatus,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
