package com.cheezycode.daggerexample

import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    val userRepository: UserRepository,
    val notificationService: NotificationService,
) {
    fun registerUser(userName: String, email: String) {
        userRepository.saveUser(userName, email)
        notificationService.sendMail(email)
    }
}