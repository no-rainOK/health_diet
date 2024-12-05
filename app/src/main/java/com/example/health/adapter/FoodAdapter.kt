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
import com.example.health.bean.FoodBean

class FoodAdapter(private val context: Context, private var mDatas: List<FoodBean>) : BaseAdapter() {

    override fun getCount(): Int = mDatas.size

    override fun getItem(position: Int): Any = mDatas[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_food, parent, false).also {
            holder = ViewHolder(it)
            it.tag = holder
        }
        holder = view.tag as ViewHolder

        val foodBean = mDatas[position]
        holder.tvTitle.text = foodBean.name
        holder.tvNot.text = "不可匹配: ${foodBean.taboo}"
        holder.tvDetail.text = foodBean.intro

        // 获取数据库中存储的资源 URI（例如 "android.resource://com.example.health/drawable/menu1"）
        val imageUri = Uri.parse(foodBean.image)  // 解析 URI

        // 使用 setImageURI 来加载图片
        if (imageUri.scheme == "android.resource") {
            holder.iv.setImageURI(imageUri)  // 设置图片
        } else {
            // 如果 URI 无效，可以设置默认图片
            holder.iv.setImageResource(R.drawable.placeholder)  // 默认图片
        }

        return view
    }

    private class ViewHolder(view: View) {
        val iv: ImageView = view.findViewById(R.id.iv_info)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvNot: TextView = view.findViewById(R.id.tv_notmatch)
        val tvDetail: TextView = view.findViewById(R.id.tv_detail)
    }
}
