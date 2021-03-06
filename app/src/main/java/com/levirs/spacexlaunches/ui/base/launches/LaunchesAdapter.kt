package com.levirs.spacexlaunches.ui.base.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.databinding.ItemLaunchBinding
import com.levirs.spacexlaunches.ui.utils.UIUtils

class LaunchesAdapter(
    val callback: Callback
) : PagingDataAdapter<LaunchEntity, LaunchesAdapter.ViewHolder>(
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
        return ViewHolder(
            ItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LaunchEntity) = with(binding) {
            tvName.text = data.name
            UIUtils.updateLaunchStateChip(binding.cpState, data.state)
            tvDate.text = com.levirs.spacexlaunches.ui.utils.LaunchDateTimeFormatter(
                root.context,
                data.datePrecision
            )
                .format(data.launchDateTime)
            if (data.smallPatch != null)
                Glide.with(root)
                    .load(data.smallPatch)
                    .placeholder(R.drawable.img_placeholder_loading)
                    .error(R.drawable.img_placeholder_broken)
                    .into(imgPatch)
            else {
                Glide.with(root)
                    .clear(imgPatch)
                imgPatch.setImageResource(R.drawable.img_rocket_blank)
            }

            root.setOnClickListener {
                callback.onItemClicked(data)
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: LaunchEntity)
    }
}
