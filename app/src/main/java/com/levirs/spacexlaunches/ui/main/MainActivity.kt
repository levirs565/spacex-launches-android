package com.levirs.spacexlaunches.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBurgerIcon: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.content.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mBurgerIcon = ActionBarDrawerToggle(
            this, binding.root, binding.content.toolbar,
            R.string.drawer_burger_open, R.string.drawer_burger_close
        )
        mBurgerIcon.isDrawerIndicatorEnabled = true
        mBurgerIcon.syncState()
        binding.root.addDrawerListener(mBurgerIcon)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mBurgerIcon.onConfigurationChanged(newConfig)
    }
}
