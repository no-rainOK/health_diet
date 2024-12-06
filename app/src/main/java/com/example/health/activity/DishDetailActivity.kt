package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.health.R
import com.example.health.databinding.ActivityDishDetailBinding

class DishDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDishDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDishDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 修改按钮
        binding.tvUpdate.setOnClickListener {
            // 获取菜品的详细信息
            val id = intent.getIntExtra("id", -1)
            val image = intent.getStringExtra("image") ?: ""
            val name = intent.getStringExtra("name") ?: ""
            val step = intent.getStringExtra("step") ?: ""

            // 跳转到修改菜品页面
            val intent = Intent(this, DishUpdateActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("image", image)
            intent.putExtra("name", name)
            intent.putExtra("step", step)
            startActivity(intent)
        }

        // 获取传递的菜品信息
        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val step = intent.getStringExtra("step")

        // 设置菜品名称和步骤
        binding.tvDishName.text = name
        binding.tvDishStep.text = step

        // 使用 Glide 加载图片
        Glide.with(this)
            .load(image) // 从 Intent 获取图片 URL
            .transform(CircleCrop())
            .placeholder(R.drawable.placeholder) // 占位图
            .into(binding.tvDishImage) // 设置图片到 ImageView
    }
}
