package com.example.health.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.health.R
import com.example.health.bean.MenuBean

class MenuAdapter(private val context: Context, private var mDatas: List<MenuBean>) : BaseAdapter() {

    override fun getCount(): Int = mDatas.size

    override fun getItem(position: Int): Any = mDatas[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val menuBean = mDatas[position]

        // 获取数据库中存储的图片资源路径
        val imageUri = Uri.parse(menuBean.image)  // 解析 URI，例如 "android.resource://com.example.health/drawable/menu1"

        // 使用 setImageURI 加载图片
        if (imageUri.scheme == "android.resource") {
            holder.iv.setImageURI(imageUri)  // 设置图片
        } else {
            // 如果 URI 无效，可以设置默认图片
            holder.iv.setImageResource(R.drawable.placeholder)  // 默认图片
        }

        holder.tv.text = menuBean.name

        return view
    }

    private class ViewHolder(view: View) {
        val iv: ImageView = view.findViewById(R.id.item_grid_iv)
        val tv: TextView = view.findViewById(R.id.item_grid_tv)
    }
}
