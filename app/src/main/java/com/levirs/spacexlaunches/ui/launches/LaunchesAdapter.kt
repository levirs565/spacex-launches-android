package com.levirs.spacexlaunches.ui.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.databinding.ItemLaunchBinding
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.ui.utils.LaunchDateTimeFormatter
import com.levirs.spacexlaunches.ui.utils.ViewUtils

class LaunchesAdapter(
    val callback: Callback
): PagingDataAdapter<LaunchEntity, LaunchesAdapter.ViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LaunchEntity>() {
            override fun areItemsTheSame(oldItem: LaunchEntity, newItem: LaunchEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: LaunchEntity, newItem: LaunchEntity): Boolean {
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

    inner class ViewHolder(val binding: ItemLaunchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LaunchEntity) = with(binding) {
            tvName.text = data.name
            ViewUtils.updateLaunchStateChip(binding.cpState, data.state)
            tvDate.text = LaunchDateTimeFormatter(root.context, data.datePrecision)
                .format(data.launchDateTime)
            if (data.smallPatch != null)
                imgPatch.load(data.smallPatch) {
                    placeholder(R.drawable.img_placeholder_loading)
                    error(R.drawable.img_placeholder_broken)
                }
            else imgPatch.load(R.drawable.img_rocket_blank)

            root.setOnClickListener {
                callback.onItemClicked(data)
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: LaunchEntity)
    }

}