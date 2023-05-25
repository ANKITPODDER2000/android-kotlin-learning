package com.example.petsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petsapp.databinding.ActivityMainBinding
import com.example.petsapp.fragment.PetCountFragment
import com.example.petsapp.fragment.PetInsertFragment
import com.example.petsapp.fragment.PetViewFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val petsCountFragment = PetCountFragment()
        val petsViewFragment = PetViewFragment()
        val petsInsertFragment = PetInsertFragment()

        replaceFragment(petsCountFragment, true)

        binding.btnViewCount.setOnClickListener { replaceFragment(petsCountFragment) }
        binding.btnViewTable.setOnClickListener { replaceFragment(petsViewFragment) }
        binding.btnInsertRecord.setOnClickListener { replaceFragment(petsInsertFragment) }
    }

    fun replaceFragment(fragment: Fragment, isFirstTimeReplace: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragmentHolder.id, fragment)
            if (!isFirstTimeReplace) addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }
}