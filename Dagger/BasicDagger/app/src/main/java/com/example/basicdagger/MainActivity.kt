package com.example.basicdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicdagger.classes.UserRegistrationService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userRegistrationService = UserRegistrationService()
        userRegistrationService.registerUser("Ankit", "ankit@demo.com")
    }
}