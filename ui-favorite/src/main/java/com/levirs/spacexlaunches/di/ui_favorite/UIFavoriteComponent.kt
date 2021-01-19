package com.levirs.spacexlaunches.di.ui_favorite

import com.levirs.spacexlaunches.di.CoreComponent
import com.levirs.spacexlaunches.di.scope.FeatureScope
import com.levirs.spacexlaunches.ui.favorite.FavoriteFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class]
)
abstract class UIFavoriteComponent {
    @Component.Factory
    interface Factory {
        fun build(coreComponent: CoreComponent): UIFavoriteComponent
    }

    abstract fun injectFragment(fragment: FavoriteFragment)
}