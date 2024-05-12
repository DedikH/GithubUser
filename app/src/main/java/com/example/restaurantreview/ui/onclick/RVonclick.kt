package com.example.restaurantreview.ui.onclick

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.fiturtambahan.MainViewModel
import com.example.restaurantreview.fiturtambahan.dataStore
import com.example.restaurantreview.fiturtambahan.settingpreverences
import com.example.restaurantreview.fiturtambahan.viewModelFactory
import com.example.restaurantreview.ui.SectionsPagerAdapter

interface RVonclick {
    fun onItemClicked(view: View, username: ItemsItem)

}