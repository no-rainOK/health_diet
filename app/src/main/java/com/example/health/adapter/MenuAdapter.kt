package com.example.health.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.health.R
import com.example.health.bean.FoodBean

class MenuAdapter(private val context: Context, private var mDatas: List<FoodBean>) : BaseAdapter() {

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

        val foodBean = mDatas[position]
        holder.iv.setImageResource(foodBean.picId)
        holder.tv.text = foodBean.title

        return view
    }

    private class ViewHolder(view: View) {
        val iv: ImageView = view.findViewById(R.id.item_grid_iv)
        val tv: TextView = view.findViewById(R.id.item_grid_tv)
    }
}
