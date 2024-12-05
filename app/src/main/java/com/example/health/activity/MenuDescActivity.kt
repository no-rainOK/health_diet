package com.example.health.activity

import android.net.Uri
import android.os.Bundle
import com.example.health.R
import com.example.health.bean.FoodBean
import com.example.health.bean.MenuBean
import com.example.health.databinding.ActivityMenuDescBinding

class MenuDescActivity : BaseActivity() {

    private lateinit var binding: ActivityMenuDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 接收上一级页面传来的数据
        val menuBean = intent.getSerializableExtra("menu") as? MenuBean

        // 设置显示控件
        menuBean?.let {
//             如果FoodBean有title、desc、notmatch和picId字段，确保这几个字段正确绑定到对应控件
            binding.tvTitle.text = it.name ?: "标题未知"
            binding.tvFoodName.text = it.name ?: "名称未知"
            binding.tvFoodDesc.text = it.intro ?: "食材未知"
            binding.tvFoodNot.text = it.step ?: "做法未知"
            // 动态加载图片
            // 获取图片路径（假设 menuBean.image 存储的是类似 "android.resource://com.example.health/drawable/menu1" 的 URI）
            val imageUri = Uri.parse(menuBean.image)

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
