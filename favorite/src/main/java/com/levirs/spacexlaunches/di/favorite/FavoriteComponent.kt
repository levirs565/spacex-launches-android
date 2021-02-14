package com.levirs.spacexlaunches.di.favorite

import com.levirs.spacexlaunches.core.CoreComponent
import com.levirs.spacexlaunches.di.scope.FeatureScope
import com.levirs.spacexlaunches.ui.favorite.FavoriteFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteViewModel::class]
)
abstract class FavoriteComponent {
    @Component.Factory
    interface Factory {
        fun build(coreComponent: CoreComponent): FavoriteComponent
    }

    abstract fun injectFragment(fragment: FavoriteFragment)
}
