package com.levirs.spacexlaunches.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.levirs.spacexlaunches.databinding.FragmentLaunchesBinding

class LaunchesFragment: Fragment() {
    private lateinit var mBinding: FragmentLaunchesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLaunchesBinding.inflate(inflater, container, false)
        return mBinding.root
    }
}