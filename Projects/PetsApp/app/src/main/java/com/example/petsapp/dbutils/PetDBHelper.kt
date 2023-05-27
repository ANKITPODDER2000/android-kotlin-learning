package com.example.petsapp.dbutils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.petsapp.entityhelper.Pet

class PetDBHelper private constructor(context: Context) {
    private val mPetDBOpenHelper: PetDBOpenHelper
    private val mDb: SQLiteDatabase

    init {
        mPetDBOpenHelper = PetDBOpenHelper(context)
        mDb = mPetDBOpenHelper.writableDatabase
    }

    companion object {
        var sInstance: PetDBHelper? = null

        fun getInstance(context: Context): PetDBHelper {
            if (sInstance == null) sInstance = PetDBHelper(context)
            return sInstance!!
        }

        fun getPetContentValue(pet: Pet): ContentValues {
            val contentValue = ContentValues()
            pet.apply {
                contentValue.put(PetContracts.PetInfo.PET_NAME, petName)
                contentValue.put(PetContracts.PetInfo.PET_AGE, petAge)
                contentValue.put(PetContracts.PetInfo.PET_BREED, petBreed)
                contentValue.put(PetContracts.PetInfo.PET_GENDER, petGender)
            }
            return contentValue
        }
    }

    fun open(): SQLiteDatabase {
        return mDb
    }

    fun insertPet(values: ContentValues?) {
        mDb.insert(PetContracts.PetInfo.TABLE_NAME, null, values)
    }

    fun getPetCount(): Int {
        val cursor = getPetRecords()
        val petCount = cursor.count
        cursor.close()
        return petCount
    }

    private fun getPetRecords(): Cursor {
        return mDb.query(
            PetContracts.PetInfo.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
}