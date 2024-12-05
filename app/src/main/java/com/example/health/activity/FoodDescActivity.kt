package com.example.health.activity

import android.net.Uri
import android.os.Bundle
import com.example.health.R
import com.example.health.bean.FoodBean
import com.example.health.databinding.ActivityFoodDescBinding

class FoodDescActivity : BaseActivity() {

    private lateinit var binding: ActivityFoodDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递的数据
        val foodBean = intent.getSerializableExtra("food") as? FoodBean

        // 设置控件内容
        foodBean?.let {
            // 设置食物名称、描述和禁忌
            binding.tvFoodName.text = it.name
            binding.tvFoodDesc.text = it.intro
            binding.tvFoodNot.text = it.taboo

            // 动态加载图片
            val imageUri = Uri.parse(foodBean.image)

            // 判断是否为有效的资源 URI
            if (imageUri.scheme == "android.resource") {
                binding.ivBigPic.setImageURI(imageUri)  // 通过 URI 加载图片
            } else {
                // 设置默认图片
                binding.ivBigPic.setImageResource(R.drawable.placeholder)  // 默认图片
            }
        }


        // 返回按钮点击事件
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
