package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.health.bean.FoodBean
import com.example.health.databinding.ActivityFoodDescBinding

class FoodDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递的数据
        val foodBean = intent.getSerializableExtra("food") as? FoodBean

        // 设置控件内容
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
