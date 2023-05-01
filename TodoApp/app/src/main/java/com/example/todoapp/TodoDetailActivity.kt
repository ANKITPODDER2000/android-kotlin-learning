package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityTodoDetailBinding
import com.example.todoapp.model.Todo
import java.util.Date

class TodoDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todo = intent.getSerializableExtra("EXTRA_TODO") as Todo
        todo.apply {
            binding.tvTodoDetailTitle.text = todoTitle
            binding.tvTodoDetailStatus.text = status.name
            binding.tvTodoDetailDescription.text = todoDescription
            binding.tvTodoDetailStartDate.text = Date(startDate).toLocaleString()
            binding.tvTodoDetailEndDate.text = Date(endData).toLocaleString()
        }

    }
}