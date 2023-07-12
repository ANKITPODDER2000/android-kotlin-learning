package com.example.newsapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper internal constructor(context: Context, db_name: String, db_version: Int) :
    SQLiteOpenHelper(context, db_name, null, db_version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NewsContract.TopNews.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}