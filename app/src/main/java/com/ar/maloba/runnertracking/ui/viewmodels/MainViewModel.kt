package com.ar.maloba.runnertracking.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.maloba.runnertracking.data.Run
import com.ar.maloba.runnertracking.repositories.MainRepository
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    val runsSortedByDate = mainRepository.getAllRunsSortByDate()
}