package com.example.restaurantreview.data.retrofit.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.data.responses.FollowingDataItem

class ItemsItemDiffCallback : DiffUtil.ItemCallback<ItemsItem>() {
    override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
        return oldItem == newItem
    }
}