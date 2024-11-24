package com.example.health.sqlite

import android.content.ContentValues
import com.example.health.bean.MenuBean
import com.example.health.utils.SqliteUtils

object MenuBusiness {
    /**
     * 插入
     */
    fun addOne(menu: MenuBean): String {
        val contentValues = ContentValues()
        contentValues.put("title", menu.title)
        contentValues.put("content", menu.content)

        val sqLiteDatabase = SqliteUtils.instance.writableDatabase
        val insert = sqLiteDatabase.insert("app_menu", null, contentValues)
        if (insert == -1L) {
            return "添加失败"
        }
        return "添加成功"
    }


    /**
     * 删除
     */
    fun deleteOne(MenuBean: MenuBean): String {
        val sqLiteDatabase = SqliteUtils.instance.writableDatabase
        val delete = sqLiteDatabase.delete("app_menu", "_id=?", arrayOf(MenuBean.id.toString()))
        if (delete == -1) {
            return "删除失败"
        }
        return "删除成功"
    }


    /**
     * 修改
     */
    fun updateOne(MenuBean: MenuBean): String {
        val contentValues = ContentValues()
        contentValues.put("title", MenuBean.title)
        contentValues.put("content", MenuBean.content)

        val sqLiteDatabase = SqliteUtils.instance.writableDatabase
        val update = sqLiteDatabase.update(
            "app_menu",
            contentValues,
            "_id=?",
            arrayOf(MenuBean.id.toString())
        )
        if (update == 0) {
            return "修改失败"
        }
        return "修改成功"
    }


    val all: List<MenuBean>
        /**
         * 查询
         */
        get() {
            var MenuBean: MenuBean
            val list: MutableList<MenuBean> = ArrayList()

            val sql = "select * from app_menu"
            val sqLiteDatabase = SqliteUtils.instance.readableDatabase
            val cursor = sqLiteDatabase.rawQuery(sql, null)
            val idIndex = cursor.getColumnIndex("_id")
            val titleIndex = cursor.getColumnIndex("title")
            val authorIndex = cursor.getColumnIndex("content")
            //把查询到的数据循环输出
            while (cursor.moveToNext()) {
                MenuBean = MenuBean(
                    cursor.getInt(idIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(authorIndex)
                )
                list.add(MenuBean)
            }
            cursor.close()
            return list
        }
}
