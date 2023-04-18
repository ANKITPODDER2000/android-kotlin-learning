package com.cheezycode.daggerexample

import dagger.Component

@Component
interface UserRegistrationServiceComponent {
    fun getUserRegistrationService(): UserRegistrationService
    fun getNotificationService() : NotificationService
}