package com.example.restaurantreview.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantreview.data.responses.FollowingDataItem
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.data.retrofit.DiffUtil.followingCallback
import com.example.restaurantreview.databinding.ItemReviewBinding


class ReviewAdapterFollowing() : ListAdapter<FollowingDataItem, ReviewAdapterFollowing.FollowingMyViewHolder>(followingCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingMyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingMyViewHolder(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: FollowingMyViewHolder, position: Int) {
      val review = getItem(position)
        holder.bind(review)


    }
    class FollowingMyViewHolder(var binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: FollowingDataItem) {
            binding.username.text = "${review.login}"
            if (review.avatarUrl != null) {
                Glide.with(itemView.context)
                    .load(review.avatarUrl)
                    .into(binding.image)
//            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowingDataItem>(){
            override fun areItemsTheSame(oldItem: FollowingDataItem, newItem: FollowingDataItem): Boolean{
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: FollowingDataItem, newItem: FollowingDataItem): Boolean {
                return oldItem == newItem
                }
            }
        }
    }
}
