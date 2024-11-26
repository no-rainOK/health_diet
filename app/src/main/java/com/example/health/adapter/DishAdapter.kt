package com.example.health.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health.R
import com.example.health.pojo.MyDish

class DishAdapter(
    private val dishList: List<MyDish>,
    private val onItemClick: (MyDish) -> Unit, // 点击事件
    private val onDeleteClick: (MyDish) -> Unit // 删除按钮点击事件
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvDishName)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishList[position]
        holder.nameTextView.text = dish.name
        holder.itemView.setOnClickListener {
            onItemClick(dish) // 单击事件
        }
        holder.deleteButton.setOnClickListener {
            onDeleteClick(dish) // 删除按钮点击事件
        }
    }

    override fun getItemCount(): Int = dishList.size
}