package com.example.health.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.health.R

class DishDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val name = intent.getStringExtra("name")
        val step = intent.getStringExtra("step")

        findViewById<TextView>(R.id.tvDishName).text = name
        findViewById<TextView>(R.id.tvDishStep).text = step
    }
}
