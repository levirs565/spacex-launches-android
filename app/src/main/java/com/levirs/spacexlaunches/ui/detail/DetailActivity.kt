package com.levirs.spacexlaunches.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.databinding.ActivityDetailBinding
import com.levirs.spacexlaunches.getAppComponent
import com.levirs.spacexlaunches.ui.utils.LaunchDateTimeFormatter
import com.levirs.spacexlaunches.ui.utils.LinkChip
import com.levirs.spacexlaunches.ui.utils.UIUtils
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    private val mNavArgs: DetailActivityArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel: DetailViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var mBinding: ActivityDetailBinding
    private val mGlideListener: RequestListener<Drawable> = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            Toast.makeText(this@DetailActivity, "Gambar gagal dimuat", Toast.LENGTH_LONG).show()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().injectDetailActivity(this)
        super.onCreate(savedInstanceState)

        mBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateCollapsingTitle()

        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }

        mBinding.fab.setOnClickListener {
            mViewModel.toggleFavorite()
        }

        mViewModel.setLaunchId(mNavArgs.launchId)
        mViewModel.launch.observe(
            this,
            {
                bindLaunch(it)
            }
        )
    }

    private fun updateCollapsingTitle() {
        mBinding.toolbarLayout.title = title
    }

    private fun bindLaunch(launch: LaunchEntity) {
        title = launch.name
        updateCollapsingTitle()

        with(mBinding.scroll) {
            tvFlightNumber.text = launch.flightNumber.toString()
            UIUtils.updateLaunchStateChip(cpState, launch.state)
            tvDate.text = LaunchDateTimeFormatter(this@DetailActivity, launch.datePrecision)
                .format(launch.launchDateTime)
            tvRocket.text = launch.rocket.name
            tvDetail.text = launch.details
            bindLinks(launch.links)
        }

        Glide.with(this)
            .load(launch.largePatch)
            .listener(mGlideListener)
            .into(mBinding.imgPatch)
        mBinding.fab.setImageResource(
            if (launch.isFavorite)
                R.drawable.ic_favorite_checked
            else R.drawable.ic_favorite_unchecked
        )
        mBinding.fab.isEnabled = true
    }

    private fun bindLinks(links: LaunchEntity.Links?) = with(mBinding.scroll) {
        cgLinks.removeAllViews()
        val linksVisibility: Int
        if (links == null) {
            linksVisibility = View.GONE
        } else {
            linksVisibility = View.VISIBLE
            listOf(
                Pair(R.string.links_webcast, links.webcast),
                Pair(R.string.links_wikipedia, links.wikipedia),
                Pair(R.string.links_article, links.article)
            ).forEach {
                if (it.second == null) return@forEach

                val chip = LinkChip(this@DetailActivity)
                chip.setText(it.first)
                chip.setLink(it.second!!)
                cgLinks.addView(chip)
            }
        }
        tvLinks.visibility = linksVisibility
        cgLinks.visibility = linksVisibility
    }
}
