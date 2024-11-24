package com.example.health.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.health.bean.FoodBean
import com.example.health.databinding.ActivityMenuDescBinding

class MenuDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 接收上一级页面传来的数据
        val foodBean = intent.getSerializableExtra("food") as? FoodBean

        // 设置显示控件
        foodBean?.let {
            binding.tvTitle.text = it.title
            binding.tvFoodName.text = it.title
            binding.tvFoodDesc.text = it.desc
            binding.tvFoodNot.text = it.notmatch
            binding.ivBigPic.setImageResource(it.picId)
        }

        // 返回按钮点击事件
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
