package com.example.restaurantreview.fiturtambahan

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class settingpreverences private constructor(private val dataStore: DataStore<Preferences>) :
    ViewModelStoreOwner {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }
    suspend fun saveThemeSetting(isDarkModeActive: Boolean){
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: settingpreverences? = null

        fun getInstance(dataStore: DataStore<Preferences>): settingpreverences{
            return INSTANCE ?: synchronized(this){
                val instance = settingpreverences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    override val viewModelStore: ViewModelStore
        get() = TODO("Not yet implemented")
}