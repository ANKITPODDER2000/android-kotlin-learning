package com.cheezycode.daggerexample

import android.util.Log
import javax.inject.Inject

private val TAG = "Repository"

interface UserRepository{
    fun saveUser(userName: String, email: String): Unit
}

class LocalRepository @Inject constructor() : UserRepository{
    override fun saveUser(userName: String, email: String): Unit {
        Log.d(TAG, "Saving user in DB")
    }
}

class FireBaseRepository @Inject constructor() : UserRepository{
    override fun saveUser(userName: String, email: String): Unit {
        Log.d(TAG, "Saving user in FireBase")
    }
}