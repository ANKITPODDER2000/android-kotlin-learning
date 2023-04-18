package com.cheezycode.daggerexample

import dagger.Component

@Component(modules = [UserRepositoryModule::class, NotificationServiceModule::class])
interface UserRegistrationServiceComponent {
    // fun getUserRegistrationService(): UserRegistrationService
    // fun getNotificationService() : NotificationService
    fun inject(mainActivity: MainActivity)
}