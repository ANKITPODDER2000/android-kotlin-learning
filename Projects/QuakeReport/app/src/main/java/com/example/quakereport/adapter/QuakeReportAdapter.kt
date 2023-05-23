package com.example.quakereport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quakereport.QuakeReport
import com.example.quakereport.databinding.QuakeReportBinding

class QuakeReportAdapter(private val quakeReportList: List<QuakeReport>) : RecyclerView.Adapter<QuakeReportAdapter.QuakeDataViewHolder>() {

    class QuakeDataViewHolder(val binding: QuakeReportBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeDataViewHolder {
        val binding = QuakeReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuakeDataViewHolder(binding)
    }

    override fun getItemCount() = quakeReportList.size

    override fun onBindViewHolder(holder: QuakeDataViewHolder, position: Int) {
        holder.binding.apply {
            tvMag.text = quakeReportList[position].mag.toString()
            tvPos1.text = quakeReportList[position].place
        }
    }
}