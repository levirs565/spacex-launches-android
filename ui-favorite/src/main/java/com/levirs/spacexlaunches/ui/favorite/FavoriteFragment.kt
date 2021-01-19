package com.levirs.spacexlaunches.ui.favorite

import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.util.ResultState
import com.levirs.spacexlaunches.ui.core.launches.AbstractLaunchesFragment
import com.levirs.spacexlaunches.ui.utils.UIUtils

class FavoriteFragment : AbstractLaunchesFragment() {
    override fun getLaunchesPage(): LiveData<ResultState<PagingData<LaunchEntity>>> {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: LaunchEntity) {
        findNavController().navigate(
            UIUtils.getLaunchDetailUri(requireContext(), item.id)
        )
    }
}