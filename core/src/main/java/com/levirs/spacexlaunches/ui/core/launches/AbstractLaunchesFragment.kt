package com.levirs.spacexlaunches.ui.core.launches

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.FragmentLaunchesBinding
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.util.ResultState

abstract class AbstractLaunchesFragment : Fragment(R.layout.fragment_launches) {
    companion object {
        val TAG = AbstractLaunchesFragment::class.java.simpleName
    }

    private lateinit var mBinding: FragmentLaunchesBinding
    private lateinit var mAdapter: LaunchesAdapter
    abstract val emptyTextResource: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentLaunchesBinding.bind(view)
        mAdapter = LaunchesAdapter(mAdapterCallback)

        mBinding.rvLaunches.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mAdapter.addLoadStateListener {
            if (it.refresh is LoadState.NotLoading &&
                it.append.endOfPaginationReached &&
                mAdapter.itemCount <= 0
            ) {
                mBinding.tvEmpty.setText(emptyTextResource)
                mBinding.tvEmpty.isVisible = true
            } else {
                mBinding.tvEmpty.isVisible = false
            }
        }

        getLaunchesPage().observe(
            viewLifecycleOwner,
            {
                Log.d(TAG, it.toString())
                var showLoading = false
                var showList = false
                var showError = false
                when {
                    it.isSuccess() -> {
                        showList = true
                        mAdapter.submitData(viewLifecycleOwner.lifecycle, it.data!!)
                    }
                    it.isLoading() -> {
                        showLoading = true
                    }
                    else -> {
                        showError = true
                        mBinding.tvError.text = getString(R.string.state_error, it.error)
                    }
                }
                mBinding.pbLoading.isVisible = showLoading
                mBinding.rvLaunches.isVisible = showList
                mBinding.llError.isVisible = showError
            }
        )

        mBinding.btnReload.setOnClickListener {
            reload()
        }
    }

    abstract fun getLaunchesPage(): LiveData<ResultState<PagingData<LaunchEntity>>>
    abstract fun onItemClick(item: LaunchEntity)
    open fun reload() {
        throw IllegalStateException("Unimplemented")
    }

    private val mAdapterCallback = object : LaunchesAdapter.Callback {
        override fun onItemClicked(item: LaunchEntity) {
            onItemClick(item)
        }
    }
}
