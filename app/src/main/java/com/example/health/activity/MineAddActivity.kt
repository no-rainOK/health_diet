package com.example.health.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health.bean.MenuBean
import com.example.health.databinding.ActivityMineAddBinding
import com.example.health.sqlite.MenuBusiness

class MineAddActivity : BaseActivity() {

    private lateinit var binding: ActivityMineAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMineAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // 返回按钮点击事件
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 添加按钮点击事件
        binding.tvAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            val menuBean = MenuBean(-1, title, content)
            val resultMessage = MenuBusiness.addOne(menuBean)
            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()

            setResult(RESULT_OK)
            finish()
        }
    }
}
