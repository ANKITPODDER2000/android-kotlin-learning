package com.example.petsapp.commondboperation

import android.content.ContentValues
import android.database.Cursor

interface IDBOperation {
    fun insertPet(value: ContentValues?)
    fun getPetCount(): Int
    fun getRecords(
        projection: Array<String>? = null,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        sortValue: String? = null,
    ): Cursor
    fun deletePet(id: Int): Boolean
}