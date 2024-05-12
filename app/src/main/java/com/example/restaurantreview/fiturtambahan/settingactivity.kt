package com.example.restaurantreview.fiturtambahan

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantreview.R
import com.example.restaurantreview.fiturtambahan.Favorites.Activity_Favorites
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.tabs.TabLayout
import org.w3c.dom.Text

class settingactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settingactivity)

        val setting = findViewById(R.id.setting) as CardView
        val favorites = findViewById(R.id.Favorites) as CardView

        setting.setOnClickListener{
            Toast.makeText(this, "Ini Setting", Toast.LENGTH_SHORT).show()
        }

        favorites.setOnClickListener{
            val intent = Intent(this, Activity_Favorites::class.java)
            startActivity(intent)
        }
        darkmode()
    }

    private fun darkmode(){
        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val tab = findViewById<TabLayout>(R.id.tabs)

        val pref = settingpreverences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, viewModelFactory(pref)).get(
            MainViewModel::class.java
        )

        mainViewModel.getThemeSettings().observe(this){
                isDarkModeActive: Boolean ->
            if(isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener{ _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }
    }
}