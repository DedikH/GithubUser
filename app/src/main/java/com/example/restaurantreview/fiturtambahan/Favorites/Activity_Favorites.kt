package com.example.restaurantreview.fiturtambahan.Favorites

import android.content.ContentValues.TAG
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.data.responses.ResponseUsers
import com.example.restaurantreview.data.retrofit.ApiConfig
import com.example.restaurantreview.databinding.ActivityFavoritesBinding
import com.example.restaurantreview.databinding.ItemReviewBinding
import com.example.restaurantreview.ui.ReviewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Activity_Favorites : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var bindingitem: ItemReviewBinding

    companion object{
        private val adapter = ReviewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        bindingitem = ItemReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        val thisdata = ""
        findUsers(thisdata)
        deletedata()
    }

    fun findUsers(thisdata: String): Cursor? {

        val userparameter= getAllDataFromDB { username ->
            var usernames = username


            if (usernames != null) {
                val client = ApiConfig.getApiServiceFavorite().getUsers(usernames)
                client.enqueue(object : Callback<ResponseUsers> {
                    override fun onResponse(
                        call: Call<ResponseUsers>,
                        response: Response<ResponseUsers>
                    ) {
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
            else{
                Toast.makeText(this, "username not found", Toast.LENGTH_SHORT).show()
            }
        }


        showLoading(true)
        return null
    }


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
//    private fun getdatafromDB(): String? {
//        val db = DBHelper(this, null)
//        val dbread = db.readableDatabase
//        val thisdata = dbread.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME , null)
//
//        if(thisdata.moveToFirst()){
//            val username = thisdata.getString(thisdata.getColumnIndex("login"))
//            thisdata.close()
//            return username
//        }else{
//            thisdata.close()
//            return null
//        }
//    }

    private fun getAllDataFromDB(callback: (String) -> Unit) {
        val db = DBHelper(this, null)
        val dbread = db.readableDatabase
        val cursor = dbread.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val username = cursor.getString(cursor.getColumnIndex("login"))
                    callback(username) // Call the function for each record
                } while (cursor.moveToNext())
            }
        } finally {
            cursor.close()
            dbread.close() // Ensure proper resource closing even on exceptions
        }
    }


    private fun deletedata(){

        val userparameter= getAllDataFromDB{username ->
        val usernames = username
        bindingitem.cardView.setOnClickListener{
            if (usernames != null) {
                deleteddata(usernames)
            }
        }
    }
}

    fun deleteddata(datacall: String):Int {
        val userparameter= getAllDataFromDB{username ->
            Log.d("USERNAME", username)}
        var datacall = userparameter.toString()
        val db = DBHelper(this, null)
        val dbdel = db.writableDatabase
        val USERNAME = "login"
        return dbdel.delete(DBHelper.TABLE_NAME, "$USERNAME=?", arrayOf(datacall))

    }

 }