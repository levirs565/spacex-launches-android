package com.levirs.spacexlaunches.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
): ViewModel() {
    val launches by lazy {
        launchesUseCase.getLaunches("", null, LaunchSortBy.FLIGHT_NUMBER_ASC)
            .asLiveData(viewModelScope.coroutineContext)
    }
}