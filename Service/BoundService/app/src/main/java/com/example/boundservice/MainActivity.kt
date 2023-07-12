package com.example.boundservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.boundservice.databinding.ActivityMainBinding
import com.example.boundservice.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setContext(this)
        viewModel.printRandomNum()
    }

    override fun onResume() {
        super.onResume()
        initButton()
    }

    private fun initButton() {
        binding.btnStartService.setOnClickListener { viewModel.startBindService() }
        binding.btnStopService.setOnClickListener { viewModel.stopBindService() }
    }

}