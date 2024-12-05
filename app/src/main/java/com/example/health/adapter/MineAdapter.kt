package com.example.health.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.health.R
import com.example.health.bean.MenuBean

class MineAdapter(private val context: Context, private var mCaidanList: List<MenuBean>) : BaseAdapter() {

    override fun getCount(): Int = mCaidanList.size

    override fun getItem(position: Int): Any = mCaidanList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            // 如果 convertView 为 null，则加载布局并创建 ViewHolder
            view = LayoutInflater.from(context).inflate(R.layout.item_mine, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            // 如果 convertView 不为 null，则直接复用 ViewHolder
            view = convertView
            holder = view.tag as ViewHolder
        }

        // 绑定数据到 ViewHolder
//        holder.titleTextView.text = mCaidanList[position].title

        return view
    }


    private class ViewHolder(view: View) {
        val titleTextView: TextView = view.findViewById(R.id.tv_mine)
    }

    fun setData(dataList: List<MenuBean>) {
        mCaidanList = dataList
        notifyDataSetChanged()
    }
}
