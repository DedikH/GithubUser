package com.example.restaurantreview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantreview.FollowingFragment
import com.example.restaurantreview.R
import com.example.restaurantreview.data.responses.FollowersData
import com.example.restaurantreview.data.responses.FollowersDataItem
import com.example.restaurantreview.data.responses.FollowingDataItem
import com.example.restaurantreview.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowersFragment : Fragment() {

    companion object {
        private const val FollowersUser = ""
        private const val TAG = "FollowingFragment"
        private val adapter = ReviewAdapterFollowers()
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
        getListQuotes(FollowersUser)
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    private fun getListQuotes(FollowersUser : String) {
        showLoading(true)
        val result = arguments?.getString("Usernames")
        val client = ApiConfig.getFollowers().getFollowers(result)
        client.enqueue(object : Callback<List<FollowersDataItem>> {
            override fun onResponse(call: Call<List<FollowersDataItem>>,
                                    response: Response<List<FollowersDataItem>>
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
            override fun onFailure(call: Call<List<FollowersDataItem>>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun SetListUsers(listUsername: List<FollowersDataItem>?) {
        val rvReview = view?.findViewById<RecyclerView>(R.id.rvReviews)
        adapter.submitList(listUsername)
        rvReview?.adapter = adapter
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showLoading(isLoading: Boolean) {
        val loading = view?.findViewById<ProgressBar>(R.id.progressBar)
        if (isLoading) {
            loading?.visibility ?: View.VISIBLE
        } else {
            loading?.visibility ?: View.GONE
        }
    }

}