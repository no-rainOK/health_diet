package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.health.bean.MenuBean
import com.example.health.databinding.ActivityMineUpdateBinding
import com.example.health.sqlite.MenuBusiness

class MenuUpdateActivity : BaseActivity() {

    private lateinit var binding: ActivityMineUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMineUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title") ?: ""
        val content = intent.getStringExtra("content") ?: ""

        // 初始化数据
        binding.etTitle.setText(title)
        binding.etContent.setText(content)

        // 返回按钮点击事件
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 更新按钮点击事件
        binding.tvUpdate.setOnClickListener {
            val updatedMenu = MenuBean(
                id,
                binding.etTitle.text.toString(),
                binding.etContent.text.toString()
            )
            MenuBusiness.updateOne(updatedMenu)
            setResult(RESULT_OK)
            finish()
        }
    }
}
