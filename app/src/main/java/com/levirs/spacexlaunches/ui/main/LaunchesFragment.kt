package com.levirs.spacexlaunches.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.levirs.spacexlaunches.databinding.FragmentLaunchesBinding
import com.levirs.spacexlaunches.getAppComponent
import javax.inject.Inject

class LaunchesFragment: Fragment() {
    companion object {
        val TAG = LaunchesFragment::class.java.simpleName
    }
    private lateinit var mBinding: FragmentLaunchesBinding
    private lateinit var mAdapter: LaunchesAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel: LaunchesViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().injectLaunchesFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLaunchesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = LaunchesAdapter()
        mBinding.rvLaunches.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mViewModel.launches.observe(viewLifecycleOwner, {
            Log.d(TAG, it.toString())
            if (it.isSuccess()) {
                mAdapter.submitData(viewLifecycleOwner.lifecycle, it.data!!.map { item ->
                    item
                })
            }
        })
    }
}