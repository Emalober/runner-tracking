package com.ar.maloba.runnertracking.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ar.maloba.runnertracking.data.Run
import com.ar.maloba.runnertracking.repositories.MainRepository
import com.ar.maloba.runnertracking.ui.SortType
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    private val runsSortedByDate = mainRepository.getAllRunsSortByDate()
    private val runsSortedByDistance = mainRepository.getAllRunsSortByDistance()
    private val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortByCaloriesBurned()
    private val runsSortedByTimeInMillis = mainRepository.getAllRunsSortByTime()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunsSortByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate) { result ->
            if(sortType == SortType.DATE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed) { result ->
            if(sortType == SortType.AVG_SPEED) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByCaloriesBurned) { result ->
            if(sortType == SortType.CALORIES_BURNED) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance) { result ->
            if(sortType == SortType.DISTANCE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByTimeInMillis) { result ->
            if(sortType == SortType.RUNNING_TIME) {
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runsSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }
}