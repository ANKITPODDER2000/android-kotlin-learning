package com.example.petsapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapp.R
import com.example.petsapp.adapter.PetAdapter
import com.example.petsapp.databinding.FragmentPetViewBinding
import com.example.petsapp.dbutils.PetDBHelper
import com.example.petsapp.entityhelper.Pet
import com.example.petsapp.entityhelper.PetHelper

class PetViewFragment : Fragment(), PetAdapter.OnDeleteClickListener {
    lateinit var pets: MutableList<Pet>
    lateinit var petAdapter: PetAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentPetViewBinding.inflate(layoutInflater, container, false)

        pets = mutableListOf(PetHelper.getRandomPet(), PetHelper.getRandomPet())
        petAdapter = PetAdapter(pets, this)
        binding.flPetList.apply {
            adapter = petAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        getDataFromTable()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFromTable() {
        val dbPets = PetDBHelper.getInstance(requireContext()).getPetRecords()
        pets.clear()
        pets.addAll(dbPets)
        petAdapter.notifyDataSetChanged()
    }

    override fun onClick(petId: Int) {
        Toast.makeText(requireContext(), "Delete Clicked with id : $petId", Toast.LENGTH_SHORT).show()
    }

}