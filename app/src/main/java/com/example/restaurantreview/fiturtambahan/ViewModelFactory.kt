package com.example.restaurantreview.fiturtambahan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class viewModelFactory(private val pref: settingpreverences) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}