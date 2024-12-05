package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
            val name = intent.getStringExtra("name") ?: ""
            val step = intent.getStringExtra("step") ?: ""

            // 跳转到修改菜品页面
            val intent = Intent(this, DishUpdateActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("name", name)
            intent.putExtra("step", step)
            startActivity(intent)
        }

        // 获取传递的菜品信息并显示
        val name = intent.getStringExtra("name")
        val step = intent.getStringExtra("step")

        findViewById<TextView>(R.id.tvDishName).text = name
        findViewById<TextView>(R.id.tvDishStep).text = step
    }
}
