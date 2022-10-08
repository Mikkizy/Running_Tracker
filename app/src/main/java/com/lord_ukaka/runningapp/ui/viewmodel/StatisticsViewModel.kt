package com.lord_ukaka.runningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.lord_ukaka.runningapp.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    val totalTimeRun = mainRepository.getTotalTime()
    val totalDistance = mainRepository.getTotalDistance()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAverageSpeed = mainRepository.getTotalSpeed()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
}