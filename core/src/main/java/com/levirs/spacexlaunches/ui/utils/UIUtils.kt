package com.levirs.spacexlaunches.ui.utils

import android.content.Context
import android.net.Uri
import com.google.android.material.chip.Chip
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity

object UIUtils {
    fun updateLaunchStateChip(chip: Chip, state: LaunchEntity.State) {
        val stateTextColor: Pair<Int, Int> = when (state) {
            LaunchEntity.State.UPCOMING -> Pair(R.string.launch_state_upcoming, R.color.grey_900)
            LaunchEntity.State.SUCCESS -> Pair(R.string.launch_state_success, R.color.green_900)
            LaunchEntity.State.FAIL -> Pair(R.string.launch_state_fail, R.color.red_900)
        }
        chip.setText(stateTextColor.first)
        chip.setChipBackgroundColorResource(stateTextColor.second)
    }

    fun getLaunchDetailUri(context: Context, id: String): Uri = Uri.parse(
        context.getString(R.string.deeplink_detail).replace("{launchId}", id)
    )
}
