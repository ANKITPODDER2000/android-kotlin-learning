package com.example.todoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTodoBinding
import com.example.todoapp.model.NewTodoViewModel
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddTodoFragment(private val todoViewModel: TodoViewModel) : Fragment() {
    private lateinit var newTodoViewModel: NewTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentAddTodoBinding.inflate(layoutInflater)
        newTodoViewModel = ViewModelProvider(this)[NewTodoViewModel::class.java]

        binding.newTodo = newTodoViewModel
        binding.todoViewModel = todoViewModel

        binding.btnAddTodo.setOnClickListener {
            handleBtnClick()
        }

        return binding.root
    }

    fun handleBtnClick() {
        if (newTodoViewModel.isValid()) {
            runBlocking {
                val savedDatasetsInfo = launch(Dispatchers.IO) {
                    todoViewModel.addNewTodo(
                        Todo(
                            newTodoViewModel.todoTitle,
                            newTodoViewModel.todoDescription,
                            newTodoViewModel.todoStartDate,
                            newTodoViewModel.todoDueDate,
                            newTodoViewModel.todoStatus
                        )
                    )
                }
                savedDatasetsInfo.join()
                newTodoViewModel.resetAllItems()
                (requireActivity() as MainActivity).binding.bnNavBar.selectedItemId =
                    R.id.home
            }
        } else {
            Toast.makeText(this.context, "Please Provide Todo details", Toast.LENGTH_SHORT)
                .show()
        }
    }
}