package com.example.restaurantreview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantreview.data.responses.FollowingData
import com.example.restaurantreview.data.responses.FollowingDataItem
import com.example.restaurantreview.data.retrofit.ApiConfig
import com.example.restaurantreview.ui.ReviewAdapterFollowing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowingFragment : Fragment() {
    companion object {
        private const val FollowingUser = ""
        private const val TAG = "FollowingFragment"
        private val adapter = ReviewAdapterFollowing()
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rvReview = requireView().findViewById<RecyclerView>(R.id.rvReviews)
        val layoutManager = LinearLayoutManager(context)
        rvReview.layoutManager = layoutManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getListQuotes(FollowingUser)
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

private fun getListQuotes(FollowingUser : String) {
    showLoading(true)
    val result = arguments?.getString("Usernames")
    val client = ApiConfig.getFollowing().getFollowing(result)
    client.enqueue(object : Callback<List<FollowingDataItem>> {
        override fun onResponse(call: Call<List<FollowingDataItem>>,
                                response: Response<List<FollowingDataItem>>
        ){
            showLoading(false)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    SetListUsers(responseBody)
                }
            } else {
                Log.e(TAG, "onFailure: ${response.message()}")
            }
        }
        override fun onFailure(call: Call<List<FollowingDataItem>>, t: Throwable) {
            showLoading(false)
            Log.e(TAG, "onFailure: ${t.message}")
        }
    })
}

    private fun SetListUsers(listUsername: List<FollowingDataItem>?) {
        val rvReview = view?.findViewById<RecyclerView>(R.id.rvReviews)
        adapter.submitList(listUsername)
        rvReview?.adapter = adapter
    }


    @SuppressLint("SuspiciousIndentation")
    private fun showLoading(isLoading: Boolean) {
    val loading = view?.findViewById<ProgressBar>(R.id.progressBarfollowing)
        if (isLoading) {
            loading?.visibility ?: View.VISIBLE
        } else {
            loading?.visibility ?: View.GONE
        }
    }
}