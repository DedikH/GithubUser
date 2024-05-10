package com.example.restaurantreview.ui

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.restaurantreview.R
import com.example.restaurantreview.data.responses.ResponseDetail
import com.example.restaurantreview.data.retrofit.ApiConfig
import com.example.restaurantreview.fiturtambahan.Favorites.DBHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class detailnya : AppCompatActivity() {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        private const val login = "indah"
        private var DetailUsers = ""

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailnya)

        var textView = findViewById(R.id.nickname) as TextView
        var strLLogin  = intent.getStringExtra("Username")
        var image = findViewById(R.id.imagedetail) as ImageView

        textView.setText(strLLogin)

        val bundle = intent.extras
        val image_url = bundle!!.getString("imageUrl")

        Glide.with(this)
            .load(image_url)
            .into(image)

        viewpager()
        if (strLLogin != null) {
            getDetailCount(strLLogin)
        }
        detailOnClick()
    }
    private fun viewpager(){
        var strLLogins  = intent.getStringExtra("Username")
        val sectionsPagerAdapter = strLLogins?.let { SectionsPagerAdapter(this, it) }
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun getDetailCount(strLLogin: String){
        var followersCount = findViewById(R.id.countFollowers) as TextView
        var followingCount = findViewById(R.id.countFollowing) as TextView

        val client = ApiConfig.getDetailApiServices().getUsersDetail(strLLogin)
        client.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(call: Call<ResponseDetail>,
                                    response: Response<ResponseDetail>
            ){
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                         followersCount.setText(responseBody.followers.toString())
                         followingCount.setText(responseBody.following.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                Toast.makeText(this@detailnya, followersCount.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun detailOnClick(){
        val btnfav = findViewById(R.id.btnfavorites) as FloatingActionButton
        val btndel = findViewById(R.id.btndelete) as FloatingActionButton
        val db = DBHelper(this, null)
        val duplicate = db.readableDatabase
        var strLLogin  = intent.getStringExtra("Username").toString()
        var adddb =db.addUser(strLLogin)
//        var test = false
           btnfav.setOnClickListener {
               db.addUser(strLLogin)
               Toast.makeText(this, strLLogin + " Added to Favorite", Toast.LENGTH_LONG).show()
               btnfav.setBackgroundResource(R.drawable.bookmark_added)

           }

        btndel.setOnClickListener{
            deletedata(strLLogin)
            Toast.makeText(this, strLLogin + " Delete to Favorite", Toast.LENGTH_LONG).show()
        }
    }

    //            Toast.makeText(this, strLLogin + " added to database", Toast.LENGTH_LONG).show()

    fun deletedata(username: String):Int {
        val db = DBHelper(this, null)
        val dbdel = db.writableDatabase
        val USERNAME = "login"
        return dbdel.delete(DBHelper.TABLE_NAME, "$USERNAME=?", arrayOf(username))

    }
}
