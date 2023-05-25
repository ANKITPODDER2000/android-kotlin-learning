package com.example.petsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapp.databinding.ActivityMainBinding
import com.example.petsapp.fragment.PetsCountFragment
import com.example.petsapp.fragment.PetsInsertFragment
import com.example.petsapp.fragment.PetsViewFragment
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val petsCountFragment = PetsCountFragment()
        val petsViewFragment = PetsViewFragment()
        val petsInsertFragment = PetsInsertFragment()

        replaceFragment(petsCountFragment, true)

        binding.btnViewCount.setOnClickListener { replaceFragment(petsCountFragment) }
        binding.btnViewTable.setOnClickListener { replaceFragment(petsViewFragment) }
        binding.btnInsertRecord.setOnClickListener { replaceFragment(petsInsertFragment) }


    }

    fun replaceFragment(fragment: Fragment, isFirstTimeReplace: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragmentHolder.id, fragment)
            if(!isFirstTimeReplace)addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }
}