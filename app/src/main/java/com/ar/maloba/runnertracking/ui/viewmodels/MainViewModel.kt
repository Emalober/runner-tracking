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

    var runs : LiveData<List<Run>>

    var sortType = MutableLiveData(SortType.DATE)

    init {
        runs = Transformations.switchMap(sortType) {
            when(it) {
                SortType.DATE -> runsSortedByDate
                SortType.RUNNING_TIME -> runsSortedByTimeInMillis
                SortType.AVG_SPEED -> runsSortedByAvgSpeed
                SortType.DISTANCE -> runsSortedByDistance
                SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned
            }
        }
    }

    fun sortRuns(type: SortType) {
        if(sortType.value != type)
            sortType.value = type
    }
}