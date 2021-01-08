package com.levirs.spacexlaunches.ui.detail

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val mNavArgs: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Toast.makeText(this, mNavArgs.launchId, Toast.LENGTH_LONG).show()
    }
}