package com.levirs.spacexlaunches.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LaunchesViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {
    data class LaunchOptions(
        val sortBy: LaunchSortBy,
        val filterByState: LaunchEntity.State?
    )

    private val mReloadEvent = MutableSharedFlow<Unit>(replay = 1)
    private val mSortBy = MutableStateFlow(LaunchSortBy.FLIGHT_NUMBER_ASC)
    private val mFilterByState = MutableStateFlow<LaunchEntity.State?>(null)
    val launches by lazy {
        combine(mReloadEvent, mSortBy, mFilterByState) { _, sortBy, filterByState ->
            LaunchOptions(sortBy, filterByState)
        }.flatMapLatest {
            launchesUseCase.getLaunches(viewModelScope, "", it.filterByState, it.sortBy)
        }.asLiveData()
    }
    val sortBy = mSortBy.asLiveData(viewModelScope.coroutineContext)
    val filterByState = mFilterByState.asLiveData(viewModelScope.coroutineContext)

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.Default) {
            mReloadEvent.emit(Unit)
        }
    }

    fun setSortBy(sortBy: LaunchSortBy) {
        mSortBy.value = sortBy
    }

    fun setFilterByState(filterByState: LaunchEntity.State?) {
        mFilterByState.value = filterByState
    }
}
