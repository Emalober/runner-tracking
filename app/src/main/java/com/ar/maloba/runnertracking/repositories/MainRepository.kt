package com.ar.maloba.runnertracking.repositories

import com.ar.maloba.runnertracking.data.Run
import com.ar.maloba.runnertracking.data.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)
    fun getAllRunsSortByDate() = runDao.getAllRunsSortByDate()
    fun getAllRunsSortByAvgSpeed() = runDao.getAllRunsSortByAvgSpeed()
    fun getAllRunsSortByDistance() = runDao.getAllRunsSortByDistance()
    fun getAllRunsSortByTime() = runDao.getAllRunsSortByTime()
    fun getAllRunsSortByCaloriesBurned() = runDao.getAllRunsSortByCaloriesBurned()
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
    fun getTotalDistanceInMeters() = runDao.getTotalDistanceInMeters()
    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

}