package com.example.basicdagger.classes

import android.util.Log

class UserRegistrationService(
    private val userRepository: UserRepository,
    private val notificationService: NotificationService,
) {
    fun registerUser(userName: String, email: String) {
        userRepository.saveUser(userName, email)
        notificationService.sendMail(email)
    }
}