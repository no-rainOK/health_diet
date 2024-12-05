package com.example.health.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.health.sqlite.DBHelper

class MenuDataUtils(context: Context) {

    private val dbHelper = DBHelper(context)

    fun insertMenuData(food: Array<String>, food1: Array<String>, resId: Array<String>, foodjianjie: Array<String>) {
        val db = dbHelper.writableDatabase

        // 确保表存在
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS menu (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                step TEXT NOT NULL,
                image TEXT NOT NULL,
                intro TEXT
            );
        """
        db.execSQL(createTableQuery)

        // 插入数据
        val insertQuery = "INSERT INTO menu(name, step, image, intro) VALUES(?, ?, ?, ?)"
        val statement = db.compileStatement(insertQuery)

        db.beginTransaction()
        try {
            for (i in food.indices) {
                statement.bindString(1, food[i])  // name
                statement.bindString(2, food1[i]) // step
                statement.bindString(3, resId[i]) // image, 注意转为 Long 类型
                statement.bindString(4, foodjianjie[i]) // intro
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
