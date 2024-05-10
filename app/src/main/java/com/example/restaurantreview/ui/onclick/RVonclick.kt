package com.example.restaurantreview.ui.onclick

import android.view.View
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.ui.SectionsPagerAdapter

interface RVonclick {
    fun onItemClicked(view: View, username: ItemsItem)
}