package com.example.petsapp.dbutils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.petsapp.commondboperation.IDBOperation
import com.example.petsapp.entityhelper.Pet

class PetDBHelper private constructor(context: Context) : IDBOperation {
    private val mPetDBOpenHelper: PetDBOpenHelper
    private val mDb: SQLiteDatabase

    init {
        mPetDBOpenHelper = PetDBOpenHelper(context)
        mDb = mPetDBOpenHelper.writableDatabase
    }

    companion object {
        private var sInstance: PetDBHelper? = null

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

    override fun insertPet(value: ContentValues?) {
        mDb.insert(PetContracts.PetInfo.TABLE_NAME, null, value)
    }

    override fun getPetCount(): Int {
        val cursor = getRecords()
        val petCount = cursor.count
        cursor.close()
        return petCount
    }

    override fun getRecords(
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortValue: String?,
    ): Cursor {
        return mDb.query(
            PetContracts.PetInfo.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortValue,
            null
        )
    }

    fun getPetRecords(): List<Pet> {
        val pets = mutableListOf<Pet>()
        val cursor = getRecords()
        while(cursor.moveToNext()) {
            val petName = cursor.getString(cursor.getColumnIndexOrThrow(PetContracts.PetInfo.PET_NAME))
            val petBreed = cursor.getString(cursor.getColumnIndexOrThrow(PetContracts.PetInfo.PET_BREED))
            val petGender = cursor.getInt(cursor.getColumnIndexOrThrow(PetContracts.PetInfo.PET_GENDER))
            val petAge = cursor.getInt(cursor.getColumnIndexOrThrow(PetContracts.PetInfo.PET_AGE))
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(PetContracts.PetInfo._ID))
            pets.add(Pet(petName, petAge, petGender, petBreed, id))
        }
        return pets
    }
}