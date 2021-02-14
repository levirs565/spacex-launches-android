package com.levirs.spacexlaunches.ui.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.core.domain.util.ResultState
import com.levirs.spacexlaunches.di.favorite.DaggerFavoriteComponent
import com.levirs.spacexlaunches.getCoreComponent
import com.levirs.spacexlaunches.ui.base.launches.AbstractLaunchesFragment
import com.levirs.spacexlaunches.ui.utils.UIUtils
import javax.inject.Inject

class FavoriteFragment : AbstractLaunchesFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel: FavoriteViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent
            .factory().build(requireContext().getCoreComponent())
            .injectFragment(this)
    }

    override fun getLaunchesPage(): LiveData<ResultState<PagingData<LaunchEntity>>> =
        mViewModel.favorite

    override fun onItemClick(item: LaunchEntity) {
        findNavController().navigate(
            UIUtils.getLaunchDetailUri(requireContext(), item.id)
        )
    }

    override val emptyTextResource: Int = R.string.favorite_empty
}
