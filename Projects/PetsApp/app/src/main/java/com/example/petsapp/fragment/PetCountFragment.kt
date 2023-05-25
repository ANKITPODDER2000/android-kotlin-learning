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

        val db = PetDBHelper.getInstance(requireContext()).open()
        val cursor =
            db.query(PetContracts.PetInfo.TABLE_NAME, null, null, null, null, null, null, null)
        binding.tvPetCount.text = "Total no of pets : ${cursor.count}"

        cursor.close()


        return binding.root
    }

}