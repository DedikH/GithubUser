package com.example.restaurantreview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restaurantreview.FollowingFragment

class SectionsPagerAdapter(activity : AppCompatActivity, val username: String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
       return 2
    }


    override fun createFragment(position: Int): Fragment {
        val Bundle = Bundle()

        Bundle.putString("Usernames", username)

        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle
            }
            1 ->{
                fragment = FollowersFragment()
                fragment.arguments = Bundle
            }

        }
        return fragment as Fragment
    }
}