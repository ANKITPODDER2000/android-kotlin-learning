package com.cheezycode.daggerexample

import android.util.Log
import javax.inject.Inject

private val TAG = "SendNotificationToUser"

interface NotificationService{
    fun sendMail(email: String): Unit
}

class EmailService @Inject constructor(): NotificationService {
    override fun sendMail(email: String) {
        Log.d(TAG, "Sending mail to : ${email}")
    }
}

class SmsService @Inject constructor() : NotificationService {
    override fun sendMail(email: String) {
        Log.d(TAG, "Sending sms to : ${email}")
    }
}