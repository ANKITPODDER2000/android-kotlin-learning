package com.example.todoapp.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.TodoDetailActivity
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeFragment(private val todoViewModel: TodoViewModel) : Fragment(), TodoAdapter.TodoEventListener {

    lateinit var alertDialog: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        alertDialog = AlertDialog.Builder(context).setIcon(R.drawable.ic_delete)
            .setTitle("Want to delete this Todo?")
        val adapter = TodoAdapter(::createAlertDialog, this)
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(container?.context)


        todoViewModel.todos.observe(this.viewLifecycleOwner) {
            adapter.updateTodoList(it)
        }
        return binding.root
    }

    private fun createAlertDialog(it: Todo) {
        alertDialog
            .setMessage("You want to delete : ${it.todoTitle} ?")
            .setPositiveButton("Yes") { _, _ ->
                runBlocking {
                    launch(Dispatchers.IO) {
                        todoViewModel.deleteTodo(it)
                    }
                }
            }
            .setNegativeButton("No") { _, _ -> }
            .create().show()
    }

    override fun onClick(todo: Todo) {
        Intent(context, TodoDetailActivity::class.java).also {
            it.putExtra("EXTRA_TODO", todo)
            startActivity(it)
        }
    }
}