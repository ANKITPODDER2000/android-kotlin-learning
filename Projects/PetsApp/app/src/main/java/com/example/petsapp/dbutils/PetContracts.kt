package com.example.petsapp.dbutils

import android.provider.BaseColumns

class PetContracts : BaseColumns {
    companion object {
        const val DATABASE_NAME = "pet.db"
        const val DATABASE_VERSION = 1
    }

    class PetInfo {

        companion object {
            const val TABLE_NAME = "pet_info"
            const val PET_NAME = "pet_name"
            const val PET_AGE = "pet_age"
            const val PET_BREED = "pet_breed"
            const val PET_GENDER = "pet_gender"
            const val _ID = "_id"

            const val PET_GENDER_FEMALE = 0
            const val PET_GENDER_MALE = 1

            const val CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "$_ID INTEGER PRIMARY KEY , " +
                    "$PET_NAME TEXT NOT NULL, " +
                    "$PET_AGE INTEGER NOT NULL, " +
                    "$PET_BREED TEXT, " +
                    "$PET_GENDER INTEGER" +
                    ");"
            const val DROP_STATEMENT = "DROP TABLE $TABLE_NAME"
        }
    }
}