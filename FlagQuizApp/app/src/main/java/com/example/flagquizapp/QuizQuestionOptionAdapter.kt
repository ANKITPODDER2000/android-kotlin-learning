package com.example.flagquizapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class QuizQuestionOptionAdapter(
    val options: List<String>,
) : Adapter<QuizQuestionOptionAdapter.QuizQuestionOptionViewHolder>() {
    inner class QuizQuestionOptionViewHolder(val view: View) : ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuizQuestionOptionViewHolder {
        return QuizQuestionOptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.quiz_question_option, parent, false)
        )
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: QuizQuestionOptionViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.tvOption).text = options[position]
    }


}