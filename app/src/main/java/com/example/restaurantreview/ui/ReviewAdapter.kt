package com.example.restaurantreview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.data.retrofit.DiffUtil.ItemsItemDiffCallback
import com.example.restaurantreview.databinding.ItemReviewBinding
import com.example.restaurantreview.ui.onclick.RVonclick


class ReviewAdapter : ListAdapter<ItemsItem, ReviewAdapter.MyViewHolder>(ItemsItemDiffCallback()) {
    var listener : RVonclick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
        holder.binding.cardView.setOnClickListener{
            listener?.onItemClicked(it, getItem(position))
        }
    }

    class MyViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ItemsItem) {
            binding.username.text = "${review.login}"
            if (review.avatarUrl != null) {
                Glide.with(itemView.context)
                    .load(review.avatarUrl)
                    .into(binding.image)
            }
            }
    }

        companion object {
            val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
                override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }