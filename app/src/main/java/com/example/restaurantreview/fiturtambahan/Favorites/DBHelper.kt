package com.example.restaurantreview.fiturtambahan.Favorites

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


import android.content.ContentValues
import android.database.Cursor
import com.example.restaurantreview.data.responses.ItemsItem


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {



    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                DATAFOTO + " TEXT, " +
                USER_USERNAME + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addUser(userusername: String, fotoAPI: String?){
        val values = ContentValues()
        values.put(DATAFOTO, fotoAPI)
        values.put(USER_USERNAME, userusername)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }



//    arrayOf(id.toString())

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }
    fun getDuplicate(userusername: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT $USER_USERNAME FROM " + TABLE_NAME + " WHERE " + USER_USERNAME + " = ?", arrayOf(userusername))
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "Favorites_DB"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "Favorites_Users"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val USER_USERNAME = "login"
        val DATAFOTO = "url"

    }
}

