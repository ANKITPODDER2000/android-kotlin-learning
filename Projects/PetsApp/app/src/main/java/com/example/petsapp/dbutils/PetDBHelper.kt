package com.example.petsapp.dbutils

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class PetDBHelper private constructor(context: Context) {
    private val mPetDBOpenHelper:PetDBOpenHelper
    init {
        mPetDBOpenHelper = PetDBOpenHelper(context)
    }

    companion object {
        var sInstance: PetDBHelper? = null

        fun getInstance(context: Context): PetDBHelper {
            if(sInstance == null) sInstance = PetDBHelper(context)
            return sInstance!!
        }
    }

    fun open(): SQLiteDatabase {
        return mPetDBOpenHelper.readableDatabase
    }
}