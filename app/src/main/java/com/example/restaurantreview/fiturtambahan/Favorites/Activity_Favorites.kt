package com.example.restaurantreview.fiturtambahan.Favorites
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantreview.data.responses.ItemsItem
import com.example.restaurantreview.databinding.ActivityFavoritesBinding
import com.example.restaurantreview.databinding.ItemReviewBinding
import com.example.restaurantreview.fiturtambahan.Favorites.FavoriteAdapter
import com.example.restaurantreview.ui.detailnya
import com.example.restaurantreview.ui.onclick.FavonClick

class Activity_Favorites : AppCompatActivity(), FavonClick{

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var bindingitem: ItemReviewBinding
    private lateinit var adapter: FavoriteAdapter

    companion object{

        private val usernameList = mutableListOf<String>()
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

        adapter = FavoriteAdapter(emptyList(), emptyList())


        getData()
    }

    private fun getData() {
        val db = DBHelper(this, null)
        val cursor = db.getName()
        cursor!!.moveToFirst()
        if (cursor != null) {
            val usernameList = mutableListOf<String>()
            val fotoList = mutableListOf<String>()

            cursor.moveToFirst()
            do {
                val data1 = cursor.getString(cursor.getColumnIndex(DBHelper.USER_USERNAME))
                val urlFoto1 = cursor.getString(cursor.getColumnIndex(DBHelper.DATAFOTO))
                usernameList.add(data1)
                fotoList.add(urlFoto1)
            }while (cursor.moveToNext())
            cursor.close()


            adapter.updateList(usernameList, fotoList)
            binding.rvReview.adapter = adapter
        }
    }


    override fun ItemClickFavorite(view: View, username: String) {
        val db = DBHelper(this, null)
        val cursor = db.getName()
        cursor!!.moveToFirst()
        val usernameList = mutableListOf<String>()
        val data1 = cursor.getString(cursor.getColumnIndex(DBHelper.USER_USERNAME))
        val fix = data1.toString()
        val data = fix.toString()


        Toast.makeText(this, fix, Toast.LENGTH_SHORT).show()
        val intents = Intent(this, detailnya::class.java)
        intents.putExtra("Username", username)
        startActivity(intents)
    }
}