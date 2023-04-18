package com.example.basicdagger.classes

import android.util.Log

class UserRegistrationService {
    val userRepository = UserRepository()
    init {
        Log.d(this::class.java.toString(), "Initialising UserRepository")
    }

    val notificationService = NotificationService()
    init {
        Log.d(this::class.java.toString(), "Initialising NotificationService")
    }

    fun registerUser(userName:String, email: String){
        userRepository.saveUser(userName, email)
        notificationService.sendMail(email)
    }
}