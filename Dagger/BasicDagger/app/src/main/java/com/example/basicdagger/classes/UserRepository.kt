package com.example.basicdagger.classes

import android.util.Log

class UserRepository {
    fun saveUser(userName: String, email: String):Unit {
        Log.d(this::class.java.toString(),"Saving user in DB")
    }
}