package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoViewBinding
import com.example.todoapp.model.Todo

class TodoAdapter(val onDeleteListener: (Todo) -> Unit, private val listener: TodoEventListener): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private var todos: MutableList<Todo> = mutableListOf()
    class TodoViewHolder(val binding: TodoViewBinding) : RecyclerView.ViewHolder(binding.root)
    interface TodoEventListener{
        fun onClick(todo: Todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoViewBinding = TodoViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
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
            it.flTodo.setOnClickListener { listener.onClick(todos[position]) }
            it.tvTitle.text = todos[position].todoTitle
            it.tvDescription.text = todos[position].todoDescription

            it.ivDelete.setOnClickListener{
                onDeleteListener(todos[position])
            }
        }
    }
}