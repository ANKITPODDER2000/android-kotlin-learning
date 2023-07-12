package com.example.runningapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RunDao {
    @Insert
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM run_info ORDER BY startTime DESC")
    fun getRunOrderedByStartTime() : LiveData<List<Run>>

    @Query("SELECT * FROM run_info ORDER BY avgSpeedInKm DESC")
    fun getRunOrderedByAvgSpeed() : LiveData<List<Run>>

    @Query("SELECT * FROM run_info ORDER BY caloriesBurn DESC")
    fun getRunOrderedByCaloriesBurn() : LiveData<List<Run>>

    @Query("SELECT * FROM run_info ORDER BY totalDistance DESC")
    fun getRunOrderedByTotalDistance() : LiveData<List<Run>>

    @Query("SELECT * FROM run_info ORDER BY totalTimiInMillis DESC")
    fun getRunOrderedByTotalTime() : LiveData<List<Run>>
}