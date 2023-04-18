package com.example.basicdagger.classes

import android.util.Log

class NotificationService {
    fun sendMail(email: String){
        Log.d(this::class.java.toString(), "Sending mail to : ${email}")
    }
}