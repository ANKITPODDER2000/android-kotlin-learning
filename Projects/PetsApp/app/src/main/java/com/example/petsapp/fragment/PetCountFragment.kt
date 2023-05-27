package com.example.petsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapp.databinding.FragmentPetCountBinding
import com.example.petsapp.dbutils.PetContracts
import com.example.petsapp.dbutils.PetDBHelper

class PetCountFragment : Fragment() {
    lateinit var binding: FragmentPetCountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPetCountBinding.inflate(layoutInflater, container, false)

        val petCount = PetDBHelper.getInstance(requireContext()).getPetCount()
        binding.tvPetCount.text = "Total no of pets : $petCount"

        return binding.root
    }

}