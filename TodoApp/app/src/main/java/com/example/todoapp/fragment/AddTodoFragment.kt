package com.example.todoapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.FragmentAddTodoBinding
import com.example.todoapp.model.NewTodoViewModel
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTodoFragment(private val todoViewModel: TodoViewModel) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentAddTodoBinding.inflate(layoutInflater)
        val newTodoViewModel = ViewModelProvider(this)[NewTodoViewModel::class.java]

        binding.newTodo = newTodoViewModel
        binding.todoViewModel = todoViewModel

        binding.btnAddTodo.setOnClickListener {
            if (newTodoViewModel.isValid()) {
                todoViewModel.addNewTodo(
                    Todo(
                        newTodoViewModel.todoTitle,
                        newTodoViewModel.todoDescription,
                        newTodoViewModel.todoStartDate,
                        newTodoViewModel.todoDueDate,
                        newTodoViewModel.todoStatus
                    )
                )
                newTodoViewModel.resetAllItems()
                (requireActivity() as MainActivity).binding.bnNavBar.selectedItemId = R.id.home
            } else {
                Toast.makeText(this.context, "Please Provide Todo details", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }
}