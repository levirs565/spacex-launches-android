package com.levirs.spacexlaunches.di.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levirs.spacexlaunches.feature.FeatureViewModelFactory
import com.levirs.spacexlaunches.feature.ViewModelKey
import com.levirs.spacexlaunches.ui.favorite.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindsFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(featureViewModelFactory: FeatureViewModelFactory):
        ViewModelProvider.Factory
}
