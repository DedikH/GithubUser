package com.example.restaurantreview.data.retrofit.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.restaurantreview.data.responses.FollowersDataItem
import com.example.restaurantreview.data.responses.FollowingDataItem

class followersCallback : DiffUtil.ItemCallback<FollowersDataItem>(){
        override fun areItemsTheSame(oldItem: FollowersDataItem, newItem: FollowersDataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FollowersDataItem, newItem: FollowersDataItem): Boolean {
            return oldItem == newItem
        }
    }