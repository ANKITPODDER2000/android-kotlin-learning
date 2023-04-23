package com.cheezycode.daggerexample

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class NotificationServiceModule {

    /*
    @Provides
    fun getNotificationService(emailService: EmailService): NotificationService{
        return emailService
    }
     */
    @Binds
    abstract fun getSmsService(smsService: SmsService): NotificationService

}
