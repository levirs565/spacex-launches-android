package com.levirs.spacexlaunches.ui.utils

import android.widget.ImageView
import coil.load
import com.google.android.material.chip.Chip
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.domain.entity.LaunchEntity

object ViewUtils {
    fun updateLaunchStateChip(chip: Chip, state: LaunchEntity.State) {
        val stateTextColor: Pair<Int, Int> = when (state) {
            LaunchEntity.State.UPCOMING -> Pair(R.string.launch_state_upcoming, R.color.grey_900)
            LaunchEntity.State.SUCCESS -> Pair(R.string.launch_state_success, R.color.green_900)
            LaunchEntity.State.FAIL -> Pair(R.string.launch_state_fail, R.color.red_900)
        }
        chip.setText(stateTextColor.first)
        chip.setChipBackgroundColorResource(stateTextColor.second)
    }
}