package com.levirs.spacexlaunches.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class LaunchesViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {
    data class LaunchOptions(
        val sortBy: LaunchSortBy,
        val filterByState: LaunchEntity.State?,
        val filterByName: String
    )

    private val mReloadEvent = MutableSharedFlow<Unit>(replay = 1)
    private val mSortBy = MutableStateFlow(LaunchSortBy.FLIGHT_NUMBER_ASC)
    private val mFilterByState = MutableStateFlow<LaunchEntity.State?>(null)
    private val mFilterByName = MutableStateFlow("")
    val launches by lazy {
        combine(
            mReloadEvent,
            mSortBy,
            mFilterByState,
            mFilterByName.debounce(100L)
        ) { _, sortBy, filterByState, filterByName ->
            LaunchOptions(sortBy, filterByState, filterByName)
        }.flatMapLatest {
            launchesUseCase.getLaunches(
                viewModelScope,
                it.filterByName,
                it.filterByState,
                it.sortBy
            )
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

    fun setFilterByName(name: String) {
        mFilterByName.value = name.trim()
    }

    fun getLastNameFilter() = mFilterByName.value
}
