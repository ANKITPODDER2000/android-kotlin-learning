package com.cheezycode.daggerexample

import android.util.Log
import javax.inject.Inject

interface NotificationService{
    fun sendMail(email: String): Unit
}

class EmailService @Inject constructor(): NotificationService {
    override fun sendMail(email: String) {
        Log.d(this::class.java.toString(), "Sending mail to : ${email}")
    }
}

class SmsService @Inject constructor(): NotificationService {
    override fun sendMail(email: String) {
        Log.d(this::class.java.toString(), "Sending sms to : ${email}")
    }
}