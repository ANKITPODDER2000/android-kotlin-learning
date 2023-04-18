package com.cheezycode.daggerexample

import dagger.Module
import dagger.Provides

@Module
class NotificationServiceModule {

    @Provides
    fun getNotificationService(emailService: EmailService): NotificationService{
        return emailService
    }
}