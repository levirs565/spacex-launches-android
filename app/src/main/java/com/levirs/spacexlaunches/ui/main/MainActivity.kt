package com.levirs.spacexlaunches.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}