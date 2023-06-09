package com.example.flagquizapp.data

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.QuizQuestionOptionBinding

private val TAG = "QuizQuestionOptionAdapter"

class QuizQuestionOptionAdapter(
    private val options: List<List<String>>,
    val updatePosition: (Int) -> Unit,
) : RecyclerView.Adapter<QuizQuestionOptionAdapter.QuizQuestionOptionViewHolder>() {

    private var currentSelectedPosition = RecyclerView.NO_POSITION

    inner class QuizQuestionOptionViewHolder(val binding: QuizQuestionOptionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuizQuestionOptionViewHolder {
        val binding =
            QuizQuestionOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizQuestionOptionViewHolder(binding)
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(
        holder: QuizQuestionOptionViewHolder,
        @SuppressLint("RecyclerView") position: Int,
    ) {
        Log.d(TAG, options.toString())
        holder.binding.tvOption.apply {
            text = options[position][0]
            if (options[position][1] == "") {
                // check whether this option is clicked or not
                background = ContextCompat.getDrawable(
                    holder.binding.root.context,
                    if (currentSelectedPosition == position) R.drawable.ic_background_border_select
                    else R.drawable.ic_background_border
                )
            } else {
                Log.d(TAG, "Change Background")
                currentSelectedPosition = NO_POSITION
                background =
                    ContextCompat.getDrawable(
                        holder.binding.root.context,
                        if (options[position][0] == options[position][1]) R.drawable.correct_ans
                        else R.drawable.wrong_ans
                    )
            }

            // Add setOnClickListener
            setOnClickListener {
                val previouslySelectedPosition = currentSelectedPosition
                currentSelectedPosition = position
                notifyItemChanged(previouslySelectedPosition)
                notifyItemChanged(currentSelectedPosition)

                updatePosition(currentSelectedPosition)
            }
        }
    }
}
