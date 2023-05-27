package com.example.petsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.petsapp.databinding.PetListBinding
import com.example.petsapp.dbutils.PetContracts
import com.example.petsapp.entityhelper.Pet

class PetAdapter(private val petList: List<Pet>, val onDeleteClickListener: OnDeleteClickListener) :
    RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    interface OnDeleteClickListener {
        fun onClick(petId: Int)
    }

    class PetViewHolder(val binding: PetListBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = PetListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun getItemCount() = petList.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.binding.apply {
            tvPetName.text = petList[position].petName
            tvPetBreed.text = petList[position].petBreed
            tvPetGender.text = when (petList[position].petGender) {
                PetContracts.PetInfo.PET_GENDER_MALE -> "M"
                else -> "F"
            }
            tvPetAge.text = "Age : ${petList[position].petAge}"

            ivDelete.setOnClickListener {
                onDeleteClickListener.onClick(petList[position].id ?: -1)
            }

        }
    }
}