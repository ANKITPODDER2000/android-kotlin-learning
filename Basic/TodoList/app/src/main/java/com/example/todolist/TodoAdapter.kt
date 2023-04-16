package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.data.Todo
import com.example.todolist.databinding.TodoItemBinding
class TodoAdapter(
    var todos: List<Todo>
) : Adapter<TodoAdapter.TodoAdapterVideHolder>() {

    inner class TodoAdapterVideHolder(var view: View, val binding: TodoItemBinding) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapterVideHolder {
        val binding:TodoItemBinding = TodoItemBinding.inflate(LayoutInflater.from(parent.context))
        return TodoAdapterVideHolder(binding.root, binding)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoAdapterVideHolder, position: Int) {
        holder.binding.tvTitle.text = todos[position].taskTitle
        holder.binding.cbCompleted.isChecked = todos[position].isCompleted
    }
}