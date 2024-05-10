package com.example.restaurantreview.ui


import android.R.attr.value
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantreview.R
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.data.responses.ResponseUsers
import com.example.restaurantreview.data.retrofit.ApiConfig
import com.example.restaurantreview.databinding.ActivityMainBinding
import com.example.restaurantreview.databinding.ItemReviewBinding
import com.example.restaurantreview.fiturtambahan.settingactivity
import com.example.restaurantreview.ui.onclick.RVonclick
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), RVonclick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ItemReviewBinding

    companion object {
        const val TAG = "MainActivity"
        private const val LoginUsers = "Arif"
        private var LoginSearch = ""
        private val adapter = ReviewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = ItemReviewBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        val floating = findViewById(R.id.floatingActionButton4) as FloatingActionButton
        floating.setOnClickListener{
           val intent = Intent(this, settingactivity::class.java)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        findUsers(LoginUsers)
        searching(LoginSearch)
        intentdetail()
    }

    private fun intentdetail(){
        adapter.listener = this
    }

//listuser Start
    private fun findUsers(LoginUsers: String) {
        showLoading(true)
        val client = ApiConfig.getApiService().getUsers(LoginUsers)
        client.enqueue(object : Callback<ResponseUsers> {
            override fun onResponse(call: Call<ResponseUsers>,
                                    response: Response<ResponseUsers>
            ){
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        SetListUsers(responseBody.items)

                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseUsers>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
//ListUserEnd


    private fun SetListUsers(listUsername: List<ItemsItem>) {
        adapter.submitList(listUsername)
        binding.rvReview.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

//    Start Of Searching
    private fun searching(querysearch : String){
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    LoginSearch = searchView.text.toString()
                    searchView.hide()
                    showLoading(false)
                        showLoading(true)
                        val client = ApiConfig.getApiService().getUsers(LoginSearch)
                        client.enqueue(object : Callback<ResponseUsers> {
                            override fun onResponse(call: Call<ResponseUsers>,
                                                    response: Response<ResponseUsers>
                            ){
                                showLoading(false)
                                if (response.isSuccessful) {
                                    val responseBody = response.body()
                                    if (responseBody != null) {
                                        SetListUsers(responseBody.items)

                                    }
                                } else {
                                    Log.e(TAG, "onFailure: ${response.message()}")
                                }
                            }

                            override fun onFailure(call: Call<ResponseUsers>, t: Throwable) {
                                showLoading(false)
                                Log.e(TAG, "onFailure: ${t.message}")
                            }
                        })
                    false
                }
        }
    }
//End Of Searching


    @SuppressLint("SuspiciousIndentation")
    override fun onItemClicked(view: View, username: ItemsItem) {

        val intents = Intent(this, detailnya::class.java)
            intents.putExtra("Username", username.login)
            val bundle = Bundle()
            bundle.putString("imageUrl",username.avatarUrl)
            intents.putExtras(bundle)
            startActivity(intents)
    }
}


