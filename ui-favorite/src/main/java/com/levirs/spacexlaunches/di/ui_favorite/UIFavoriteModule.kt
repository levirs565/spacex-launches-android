package com.levirs.spacexlaunches.di.ui_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levirs.spacexlaunches.di.map.ViewModelKey
import com.levirs.spacexlaunches.ui.favorite.FavoriteViewModel
import com.levirs.spacexlaunches.ui.utils.FeatureViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UIFavoriteModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindsFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(featureViewModelFactory: FeatureViewModelFactory):
            ViewModelProvider.Factory
}