package com.lord_ukaka.runningapp.repositories

import androidx.lifecycle.LiveData
import com.lord_ukaka.runningapp.db.Run
import com.lord_ukaka.runningapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDao
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate(): LiveData<List<Run>> = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByTime(): LiveData<List<Run>> = runDao.getAllRunsSortedByTime()

    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>> = runDao.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>> = runDao.getAllRunsSortedByCaloriesBurned()

    fun getAllRunsSortedByDistance(): LiveData<List<Run>> = runDao.getAllRunsSortedByDistance()

    fun getTotalTime(): LiveData<Long> = runDao.getTotalTime()

    fun getTotalCaloriesBurned(): LiveData<Int> = runDao.getTotalCaloriesBurned()

    fun getTotalDistance(): LiveData<Int> = runDao.getTotalDistance()

    fun getTotalSpeed(): LiveData<Float> = runDao.getTotalSpeed()
}