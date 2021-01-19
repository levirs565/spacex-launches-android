package com.levirs.spacexlaunches.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase

class FavoriteViewModel(
    private val launchesUseCase: LaunchesUseCase
): ViewModel() {
    val favorite by lazy {
        launchesUseCase.getFavoriteLaunches(viewModelScope)
    }
}