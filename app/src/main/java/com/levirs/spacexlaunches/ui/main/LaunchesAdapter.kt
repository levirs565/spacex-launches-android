package com.levirs.spacexlaunches.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ItemLaunchBinding
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import org.threeten.bp.format.DateTimeFormatter

class LaunchesAdapter: PagingDataAdapter<LaunchEntity, LaunchesAdapter.ViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LaunchEntity>() {
            override fun areItemsTheSame(oldItem: LaunchEntity, newItem: LaunchEntity): Boolean {
                Log.d(LaunchesFragment.TAG, "Items same")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: LaunchEntity, newItem: LaunchEntity): Boolean {
                Log.d(LaunchesFragment.TAG, "Contents same")
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLaunchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    class ViewHolder(val binding: ItemLaunchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LaunchEntity) = with(binding) {
            Log.d(LaunchesFragment.TAG, "Bind-bind ")
            tvName.text = data.name
            cpState.setText(when (data.state) {
                LaunchEntity.State.UPCOMING -> R.string.launch_state_upcoming
                LaunchEntity.State.SUCCESS -> R.string.launch_state_success
                LaunchEntity.State.FAIL -> R.string.launch_state_fail
            })
            tvDate.text = data.launchDateTime.format(DateTimeFormatter.ISO_DATE)
        }
    }

}