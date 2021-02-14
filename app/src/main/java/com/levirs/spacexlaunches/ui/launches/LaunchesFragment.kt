package com.levirs.spacexlaunches.ui.launches

import android.app.SearchManager
import android.app.Service
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.core.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.core.domain.util.ResultState
import com.levirs.spacexlaunches.getAppComponent
import com.levirs.spacexlaunches.ui.core.launches.AbstractLaunchesFragment
import com.levirs.spacexlaunches.ui.utils.UIUtils
import com.levirs.spacexlaunches.ui.utils.getKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class LaunchesFragment : AbstractLaunchesFragment() {
    companion object {
        val TAG = LaunchesFragment::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel: LaunchesViewModel by viewModels {
        viewModelFactory
    }
    private val mMenuSortByMap = mapOf(
        LaunchSortBy.FLIGHT_NUMBER_ASC to R.id.sort_by_flight_number_asc,
        LaunchSortBy.FLIGHT_NUMBER_DESC to R.id.sort_by_flight_number_desc,
        LaunchSortBy.NAME_ASC to R.id.sort_by_name_asc,
        LaunchSortBy.NAME_DESC to R.id.sort_by_name_desc
    )
    private val mMenuFilterMap = mapOf(
        null to R.id.filter_state_none,
        LaunchEntity.State.SUCCESS to R.id.filter_state_success,
        LaunchEntity.State.FAIL to R.id.filter_state_fail,
        LaunchEntity.State.UPCOMING to R.id.filter_state_upcoming
    )

    override fun getLaunchesPage(): LiveData<ResultState<PagingData<LaunchEntity>>> =
        mViewModel.launches

    override fun reload() {
        mViewModel.reload()
    }

    override val emptyTextResource: Int = R.string.launches_empty

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().injectLaunchesFragment(this)
        setHasOptionsMenu(true)
    }

    override fun onItemClick(item: LaunchEntity) {
        findNavController().navigate(
            UIUtils.getLaunchDetailUri(requireContext(), item.id)
        )
    }

    private val mQueryTextListener =
        object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null) return false

                mViewModel.setFilterByName(newText)
                return true
            }
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_launches, menu)
        mViewModel.sortBy.observe(
            viewLifecycleOwner,
            {
                menu.findItem(mMenuSortByMap.getValue(it)).isChecked = true
            }
        )
        mViewModel.filterByState.observe(
            viewLifecycleOwner,
            {
                menu.findItem(mMenuFilterMap.getValue(it)).isChecked = true
            }
        )

        val searchItem = menu.findItem(R.id.search_name)
        val searchView = searchItem.actionView as SearchView
        searchView.setIconifiedByDefault(true)
        searchView.setOnQueryTextListener(mQueryTextListener)
        searchView.maxWidth = Int.MAX_VALUE

        mViewModel.getLastNameFilter().also {
            if (it.isEmpty()) return@also

            searchItem.expandActionView()
            searchView.setQuery(it, false)
        }

        val searchManager =
            requireContext().getSystemService(Service.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(requireActivity().componentName)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sort_by) {
            mViewModel.setSortBy(mMenuSortByMap.getKey(item.itemId))
            return true
        } else if (item.groupId == R.id.filter_state) {
            mViewModel.setFilterByState(mMenuFilterMap.getKey(item.itemId))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
