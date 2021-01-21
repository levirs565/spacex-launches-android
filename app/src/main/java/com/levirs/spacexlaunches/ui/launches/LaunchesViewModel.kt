package com.levirs.spacexlaunches.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LaunchesViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
): ViewModel() {
    private val mReloadEvent = MutableSharedFlow<Unit>(replay = 1)
    val launches by lazy {
        mReloadEvent.flatMapLatest {
            launchesUseCase.getLaunches(viewModelScope, "", null, LaunchSortBy.FLIGHT_NUMBER_ASC)
        }.asLiveData()
    }

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.Default) {
            mReloadEvent.emit(Unit)
        }
    }
}