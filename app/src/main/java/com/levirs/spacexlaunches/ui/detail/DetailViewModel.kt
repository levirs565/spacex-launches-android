package com.levirs.spacexlaunches.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.core.domain.usecase.LaunchesUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class DetailViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {
    private lateinit var mLaunchId: String
    lateinit var launch: LiveData<LaunchEntity>

    fun setLaunchId(launchId: String) {
        if (this::mLaunchId.isInitialized && mLaunchId == launchId)
            return

        mLaunchId = launchId
        launch = launchesUseCase.getLaunchById(mLaunchId)
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun toggleFavorite() = viewModelScope.launch {
        val entity = launch.value!!
        launchesUseCase.toggleFavoriteLaunch(entity)
    }
}
