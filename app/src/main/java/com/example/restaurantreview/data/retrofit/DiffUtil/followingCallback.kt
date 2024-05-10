package com.example.restaurantreview.data.retrofit.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.restaurantreview.data.responses.FollowingDataItem

class followingCallback : DiffUtil.ItemCallback<FollowingDataItem>(){
        override fun areItemsTheSame(oldItem: FollowingDataItem, newItem: FollowingDataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FollowingDataItem, newItem: FollowingDataItem): Boolean {
            return oldItem == newItem
        }
    }