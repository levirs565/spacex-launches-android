package com.levirs.spacexlaunches.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.core.domain.usecase.LaunchesUseCase
import com.levirs.spacexlaunches.core.domain.util.ResultState
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {
    val favorite by lazy {
        launchesUseCase.getFavoriteLaunches(viewModelScope)
            .map { ResultState.succes(it) }
            .asLiveData(viewModelScope.coroutineContext)
    }
}
