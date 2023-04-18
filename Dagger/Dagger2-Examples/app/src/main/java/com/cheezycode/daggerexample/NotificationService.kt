package com.cheezycode.daggerexample

import android.util.Log
import javax.inject.Inject

class NotificationService @Inject constructor() {
    fun sendMail(email: String) {
        Log.d(this::class.java.toString(), "Sending mail to : ${email}")
    }
}