package com.example.health.sqlite

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.health.bean.FoodBean
import com.example.health.bean.MenuBean

class DatabaseHelper(context: Context) {

    private val dbHelper: DBHelper = DBHelper(context)



    // 获取所有食品数据
    @SuppressLint("Range")
    fun getAllFoodData(): List<FoodBean> {
        val foodList = mutableListOf<FoodBean>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM food", null)
//        Log.d("Database", "Query executed: SELECT * FROM food")

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val taboo = cursor.getString(cursor.getColumnIndex("taboo"))
                val image = cursor.getString(cursor.getColumnIndex("image"))
                val intro = cursor.getString(cursor.getColumnIndex("intro"))
                val foodBean = FoodBean(name, taboo, image, intro)
                foodList.add(foodBean)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return foodList
    }

    // 获取所有菜单数据
    @SuppressLint("Range")
    fun getAllMenuData(): List<MenuBean> {
        val menuList = mutableListOf<MenuBean>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM menu", null)


        if (cursor != null && cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val step = cursor.getString(cursor.getColumnIndex("step"))
                val image = cursor.getString(cursor.getColumnIndex("image"))
                val intro = cursor.getString(cursor.getColumnIndex("intro"))
                val menuBean = MenuBean(name, step, image, intro)
                menuList.add(menuBean)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return menuList
    }

    private class DBHelper(context: Context) : SQLiteOpenHelper(context, "health_diet.db", null, 2) {
        override fun onCreate(db: SQLiteDatabase?) {
            Log.d("Database", "onCreate called")
            // 创建 food 表
            val createFoodTableQuery = """
            CREATE TABLE IF NOT EXISTS food (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                taboo TEXT NOT NULL,
                image TEXT NOT NULL,
                intro TEXT
            );
        """
            db?.execSQL(createFoodTableQuery)

            // 创建 menu 表
            val createMenuTableQuery = """
            CREATE TABLE IF NOT EXISTS menu (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            step TEXT NOT NULL,
            image TEXT NOT NULL,
            intro TEXT
            );
        """
            db?.execSQL(createMenuTableQuery)
        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS food")
            db?.execSQL("DROP TABLE IF EXISTS menu")
            onCreate(db)
        }
    }
}
