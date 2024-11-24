package com.example.health.utils

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteUtils private constructor() : SQLiteOpenHelper(
    AppUtils.application, "health_diet.db", null, 1
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)")
        db.execSQL("CREATE TABLE app_menu (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
        db.execSQL("DROP TABLE IF EXISTS app_menu")
        onCreate(db)
    }

    companion object {
        val instance: SqliteUtils by lazy { SqliteUtils() }
    }
}
