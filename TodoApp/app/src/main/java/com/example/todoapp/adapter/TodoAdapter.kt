package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoViewBinding
import com.example.todoapp.model.Todo

class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    var todos: MutableList<Todo> = mutableListOf()
    class TodoViewHolder(val binding: TodoViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var todoViewBinding = TodoViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TodoViewHolder(todoViewBinding)
    }

    fun updateTodoList(newTodos: List<Todo>){
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.also {
            it.tvTitle.text = todos[position].todoTitle
            it.tvDescription.text = todos[position].todoDescription
        }
    }
}