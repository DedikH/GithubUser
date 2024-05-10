package com.example.restaurantreview.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantreview.data.responses.FollowersDataItem
import com.example.restaurantreview.data.responses.FollowingDataItem
import com.example.restaurantreview.data.retrofit.DiffUtil.followersCallback
import com.example.restaurantreview.databinding.ItemReviewBinding


class ReviewAdapterFollowers() : ListAdapter<FollowersDataItem, ReviewAdapterFollowers.FollowersMyViewHolder>(followersCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersMyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersMyViewHolder(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: FollowersMyViewHolder, position: Int) {
      val review = getItem(position)
        holder.bind(review)


    }
    class FollowersMyViewHolder(var binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: FollowersDataItem) {
            binding.username.text = "${review.login}"
            if (review.avatarUrl != null) {
                Glide.with(itemView.context)
                    .load(review.avatarUrl)
                    .into(binding.image)
//            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowersDataItem>(){
            override fun areItemsTheSame(oldItem: FollowersDataItem, newItem: FollowersDataItem): Boolean{
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: FollowersDataItem, newItem: FollowersDataItem): Boolean {
                return oldItem == newItem
                }
            }
        }
    }
}
