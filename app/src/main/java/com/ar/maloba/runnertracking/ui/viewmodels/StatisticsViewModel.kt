package com.ar.maloba.runnertracking.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ar.maloba.runnertracking.repositories.MainRepository
import javax.inject.Inject


class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {
}