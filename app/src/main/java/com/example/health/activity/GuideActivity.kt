package com.example.health.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.health.R
import com.example.health.adapter.GuideAdapter
import com.example.health.databinding.ActivityGuideBinding

class GuideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuideBinding
    private val viewList = mutableListOf<View>() // ViewPager的数据源
    private val numList = mutableListOf<TextView>() // 页码指示器集合
    private val resId = intArrayOf(
        R.drawable.ic_home_menu_tips1,
        R.drawable.ic_home_menu_tips2,
        R.drawable.ic_home_menu_tips3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        // 设置按钮监听器
        binding.btnGuide.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 添加页码指示器
        numList.add(binding.tvGuide1)
        numList.add(binding.tvGuide2)
        numList.add(binding.tvGuide3)

        // 初始化 ViewPager 页面资源
        resId.forEach { id ->
            val view = LayoutInflater.from(this).inflate(R.layout.item_guide1, null)
            val ivImage = view.findViewById<ImageView>(R.id.iv_image)
            ivImage.setImageResource(id)
            viewList.add(view)
        }

        // 创建适配器对象并设置
        val adapter = GuideAdapter(viewList)
        binding.vpGuide.adapter = adapter
        binding.tvGuide1.setTextColor(Color.RED)

        // 设置 ViewPager 的监听
        setVPListener()
    }

    private fun setVPListener() {
        binding.vpGuide.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                numList.forEach { it.setTextColor(Color.WHITE) }
                numList[position].setTextColor(Color.RED)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
