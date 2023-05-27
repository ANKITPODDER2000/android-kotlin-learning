package com.example.petsapp.dbutils

import kotlin.random.Random

class PetHelper {
    companion object {
        fun getRandomPet() : Pet {
            val allowedChars = ('a'..'z')
            var petName = (1..Random.nextInt(6,12)).map { allowedChars.random() }.joinToString("")
            val petAge = Random.nextInt(5,10)
            val petGender = Random.nextInt(0,2)
            var petBreed = (1..Random.nextInt(6,12)).map { allowedChars.random() }.joinToString("")

            petName = petName[0].toString().uppercase() + petName.substring(1)
            petBreed = petBreed[0].toString().uppercase() + petBreed.substring(1)

            return Pet(
                petName, petAge, petGender, petBreed
            )
        }
    }
}