package com.example.runningapplication.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "run_info")
data class Run(
    @PrimaryKey val id: Int? = null,
    val img: Bitmap? = null,
    val startTime: Long = 0,
    val avgSpeedInKm: Int = 0,
    val totalDistance: Int =0,
    val totalTimiInMillis: Long = 0,
    val caloriesBurn: Int = 0
)
