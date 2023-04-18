package com.cheezycode.daggerexample

import android.util.Log
import javax.inject.Inject

interface UserRepository{
    fun saveUser(userName: String, email: String): Unit
}

class LocalRepository @Inject constructor() : UserRepository{
    override fun saveUser(userName: String, email: String): Unit {
        Log.d(this::class.java.toString(), "Saving user in DB")
    }
}

class FireBaseRepository @Inject constructor() : UserRepository{
    override fun saveUser(userName: String, email: String): Unit {
        Log.d(this::class.java.toString(), "Saving user in FireBase")
    }
}