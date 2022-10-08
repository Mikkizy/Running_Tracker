package com.lord_ukaka.runningapp.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lord_ukaka.runningapp.db.Run
import com.lord_ukaka.runningapp.other.SortType
import com.lord_ukaka.runningapp.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    private val runSortedByDate = mainRepository.getAllRunsSortedByDate()
    private val runSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCaloriesBurned()
    private val runSortedByTime = mainRepository.getAllRunsSortedByTime()
    private val runSortedByAverageSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

    //The mediatorLiveData object helps you observe the emissions of various livedata objects
    // and merges them if needed, or reacts on changes or events from them. It basically helps you
    // carry out specific actions on them.
    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runSortedByDate) { result ->
            if (sortType == SortType.DATE) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(runSortedByDistance) { result ->
            if (sortType == SortType.DISTANCE) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(runSortedByAverageSpeed) { result ->
            if (sortType == SortType.AVG_SPEED) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(runSortedByCaloriesBurned) { result ->
            if (sortType == SortType.CALORIES_BURNED) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(runSortedByTime) { result ->
            if (sortType == SortType.RUNNING_TIME) {
                result?.let {
                    runs.value = it
                }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runSortedByDate.value?.let {
            runs.value = it
        }
        SortType.DISTANCE -> runSortedByDistance.value?.let {
            runs.value = it
        }
        SortType.RUNNING_TIME -> runSortedByTime.value?.let {
            runs.value = it
        }
        SortType.AVG_SPEED -> runSortedByAverageSpeed.value?.let {
            runs.value = it
        }
        SortType.CALORIES_BURNED -> runSortedByCaloriesBurned.value?.let {
            runs.value = it
        }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

}