package com.example.basicdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicdagger.classes.NotificationService
import com.example.basicdagger.classes.UserRegistrationService
import com.example.basicdagger.classes.UserRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userRepository= UserRepository()
        val notificationService = NotificationService()

        val userRegistrationService = UserRegistrationService(userRepository, notificationService)
        userRegistrationService.registerUser("Ankit", "ankit@demo.com")
    }
}