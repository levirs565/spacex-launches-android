package com.levirs.spacexlaunches.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.levirs.spacexlaunches.databinding.ActivityDetailBinding
import com.levirs.spacexlaunches.getAppComponent
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    private val mNavArgs: DetailActivityArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel: DetailViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var mBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().injectDetailActivity(this)
        super.onCreate(savedInstanceState)

        mBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        updateCollapsingTitle()
        mBinding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mViewModel.setLaunchId(mNavArgs.launchId)
        mViewModel.launch.observe(this, {
            title = it.name
            updateCollapsingTitle()
        })
    }

    private fun updateCollapsingTitle() {
        mBinding.toolbarLayout.title = title
    }
}