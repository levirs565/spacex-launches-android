package com.levirs.spacexlaunches.ui.launches

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.util.ResultState
import com.levirs.spacexlaunches.getAppComponent
import com.levirs.spacexlaunches.ui.core.launches.AbstractLaunchesFragment
import javax.inject.Inject

class LaunchesFragment: AbstractLaunchesFragment() {
    companion object {
        val TAG = LaunchesFragment::class.java.simpleName
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel: LaunchesViewModel by viewModels {
        viewModelFactory
    }

    override fun getLaunchesPage(): LiveData<ResultState<PagingData<LaunchEntity>>>
        = mViewModel.launches

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().injectLaunchesFragment(this)
    }

    override fun onItemClick(item: LaunchEntity) {
        findNavController().navigate(
            LaunchesFragmentDirections
                .actionFragmentLaunchesToDetailActivity(item.id)
        )
    }
}