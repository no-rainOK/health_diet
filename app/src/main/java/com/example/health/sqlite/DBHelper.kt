package com.example.health.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "health_diet.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS menu (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                step TEXT NOT NULL,
                image TEXT NOT NULL,
                intro TEXT
            );
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 如果数据库版本升级时，可以在这里进行表结构变更
        db?.execSQL("DROP TABLE IF EXISTS menu")
        onCreate(db)
    }
}
