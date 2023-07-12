package com.example.runningapplication.repositories

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.runningapplication.db.Run
import com.example.runningapplication.db.RunDao
import javax.inject.Inject

class EntryRepository @Inject constructor(private val runDao: RunDao) {
    suspend fun insertRun(run: Run) {
        runDao.insertRun(run)
    }
    suspend fun deleteRun(run: Run) {
        runDao.deleteRun(run)
    }
    fun getRunOrderedByStartTime() = runDao.getRunOrderedByStartTime()
    fun getRunOrderedByAvgSpeed() = runDao.getRunOrderedByAvgSpeed()
    fun getRunOrderedByCaloriesBurn() = runDao.getRunOrderedByCaloriesBurn()
    fun getRunOrderedByTotalDistance() = runDao.getRunOrderedByTotalDistance()
    fun getRunOrderedByTotalTime() = runDao.getRunOrderedByTotalTime()
}