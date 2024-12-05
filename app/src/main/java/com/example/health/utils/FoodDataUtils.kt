package com.example.health.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FoodDataUtils(context: Context) {

    private val dbHelper: DBHelper = DBHelper(context)

    private class DBHelper(context: Context) : SQLiteOpenHelper(context, "health_diet.db", null, 2) {
        override fun onCreate(db: SQLiteDatabase?) {
            val createTableQuery = """
                CREATE TABLE IF NOT EXISTS food (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    taboo TEXT NOT NULL,
                    image TEXT NOT NULL,
                    intro TEXT
                );
            """
            db?.execSQL(createTableQuery)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS food")
            onCreate(db)
        }
    }

    // 插入数据
    fun insertFoodData(food: Array<String>, food1: Array<String>, resId: Array<String>, foodjianjie: Array<String>) {
        val db = dbHelper.writableDatabase
        val insertQuery = "INSERT INTO food(name, taboo, image, intro) VALUES(?, ?, ?, ?)"
        val statement = db.compileStatement(insertQuery)

        db.beginTransaction()
        try {
            for (i in food.indices) {
                statement.bindString(1, food[i])           // name
                statement.bindString(2, food1[i])          // taboo
                statement.bindString(3, resId[i])          // image (路径字符串)
                statement.bindString(4, foodjianjie[i])    // intro
                statement.executeInsert()
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }
}
