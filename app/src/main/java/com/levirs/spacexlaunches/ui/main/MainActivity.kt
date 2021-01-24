package com.levirs.spacexlaunches.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.content.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentLaunches, R.id.favoriteFragment
            ),
            binding.root
        )
        binding.navView.setupWithNavController(navController)
        binding.content.toolbar.setupWithNavController(navController, appBarConfiguration)

        title = navController.currentDestination?.label
    }
}
