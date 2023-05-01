package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.fragment.AddTodoFragment
import com.example.todoapp.fragment.HomeFragment
import com.example.todoapp.fragment.ProfileFragment
import com.example.todoapp.model.TodoDataBase
import com.example.todoapp.model.TodoViewModel
import com.example.todoapp.model.TodoViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoViewModel = ViewModelProvider(this, TodoViewModelFactory(applicationContext))[TodoViewModel::class.java]

        val homeFragment = HomeFragment(todoViewModel)
        val addTodoFragment = AddTodoFragment(todoViewModel)
        val profileFragment = ProfileFragment()
        replaceFragment(homeFragment)

        binding.bnNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.task -> replaceFragment(addTodoFragment)
                R.id.account -> replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also {
            it.setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            it.replace(binding.flFragment.id, fragment)
            it.addToBackStack(null)
            it.commit()
        }
    }
}