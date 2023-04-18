package com.cheezycode.daggerexample

import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun saveUser(userName: String, email: String): Unit {
        Log.d(this::class.java.toString(), "Saving user in DB")
    }
}