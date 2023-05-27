package com.example.petsapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.petsapp.R
import com.example.petsapp.databinding.FragmentPetInsertBinding
import com.example.petsapp.entityhelper.Pet
import com.example.petsapp.dbutils.PetContracts.PetInfo.Companion.PET_GENDER_FEMALE
import com.example.petsapp.dbutils.PetContracts.PetInfo.Companion.PET_GENDER_MALE
import com.example.petsapp.dbutils.PetDBHelper
import com.example.petsapp.entityhelper.PetHelper

class PetInsertFragment : Fragment() {
    private lateinit var binding: FragmentPetInsertBinding
    private var mSelectedGender = "Male"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPetInsertBinding.inflate(layoutInflater, container, false)

        initSpinner()
        initButton()

        return binding.root
    }

    private fun isCorrectInput(): Boolean {
        return binding.tvPetName.text.isNotEmpty() && binding.tvPetBreed.text.isNotEmpty() && binding.tvPetAge.text.isNotEmpty() && binding.tvPetAge.text.toString()
            .toInt() > 0
    }

    private fun initButton() {
        binding.btnInsertRandom.setOnClickListener {
            val randomPet = PetHelper.getRandomPet()
            val randomPetContentValue = PetDBHelper.getPetContentValue(randomPet)
            PetDBHelper.getInstance(requireContext()).insertPet(randomPetContentValue)
            showToast("Inserted Random Pet...")
        }

        binding.btnInsetMyPet.setOnClickListener {
            if (isCorrectInput()) {
                val pet = binding.run {
                    Pet(
                        tvPetName.text.toString(),
                        tvPetAge.text.toString().toInt(),
                        getPetGender(),
                        tvPetBreed.text.toString()
                    )
                }
                val petContentValue = PetDBHelper.getPetContentValue(pet)
                PetDBHelper.getInstance(requireContext()).insertPet(petContentValue)
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                showToast("Inserted Your Pet...")
                resetFields()
            } else
                showToast("Please enter all details....")
        }

    }

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun resetFields() {
        binding.tvPetName.setText("")
        binding.tvPetAge.setText("")
        binding.tvPetBreed.setText("")
    }

    private fun getPetGender(): Int {
        return if (mSelectedGender == resources.getString(R.string.male_pet)) PET_GENDER_MALE else PET_GENDER_FEMALE
    }

    private fun initSpinner() {
        val genderList =
            listOf(resources.getString(R.string.male_pet), resources.getString(R.string.female_pet))
        val adapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            genderList
        )
        binding.spPetGender.adapter = adapter
        binding.spPetGender.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val selectedGender = parent?.getItemAtPosition(position) as String
                mSelectedGender = selectedGender
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

}