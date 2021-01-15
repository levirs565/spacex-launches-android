package com.levirs.spacexlaunches.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import coil.load
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ActivityDetailBinding
import com.levirs.spacexlaunches.getAppComponent
import com.levirs.spacexlaunches.ui.utils.LaunchDateTimeFormatter
import com.levirs.spacexlaunches.ui.utils.ViewUtils
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

        mBinding.fab.setOnClickListener {
            mViewModel.toggleFavorite()
        }

        mViewModel.setLaunchId(mNavArgs.launchId)
        mViewModel.launch.observe(this, {
            title = it.name
            updateCollapsingTitle()

            with (mBinding.scroll) {
                ViewUtils.updateLaunchStateChip(cpState, it.state)
                tvDate.text = LaunchDateTimeFormatter(this@DetailActivity, it.datePrecision)
                    .format(it.launchDateTime)
            }

            mBinding.imgPatch.load(it.largePatch)
            mBinding.fab.setImageResource(if (it.isFavorite)
                R.drawable.ic_favorite_checked
            else R.drawable.ic_favorite_unchecked)
            mBinding.fab.isEnabled = true
        })
    }

    private fun updateCollapsingTitle() {
        mBinding.toolbarLayout.title = title
    }
}