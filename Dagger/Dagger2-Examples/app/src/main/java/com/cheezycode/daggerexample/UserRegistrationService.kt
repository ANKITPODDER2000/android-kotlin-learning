package com.cheezycode.daggerexample

import javax.inject.Inject
import javax.inject.Named

class UserRegistrationService @Inject constructor(
    @Named("fb") private val userRepository: UserRepository,
    private val notificationService: NotificationService,
) {
    fun registerUser(userName: String, email: String) {
        userRepository.saveUser(userName, email)
        notificationService.sendMail(email)
    }
}