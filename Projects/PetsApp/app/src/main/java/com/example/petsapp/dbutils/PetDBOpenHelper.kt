package com.example.petsapp.dbutils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class PetDBOpenHelper(val context: Context): SQLiteOpenHelper(context, PetContracts.DATABASE_NAME, null, PetContracts.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("DEBUG_ANKIT", "OnCreate is Called...")
        db?.run {
            beginTransaction()
            execSQL(PetContracts.PetInfo.CREATE_STATEMENT)
            setTransactionSuccessful()
            endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(PetContracts.PetInfo.DROP_STATEMENT)
    }
}