package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.health.R
import com.example.health.adapter.AboutAdapter
import com.example.health.databinding.ActivityHomeMenuBinding
import com.example.health.activity.BaseActivity

class HomeMenuActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeMenuBinding
    private val viewList = mutableListOf<View>() // ViewPager 数据源
    private val pointList = mutableListOf<ImageView>() // 指示器集合
    private val picIds = intArrayOf(
        R.drawable.ic_home_menu_tips1,
        R.drawable.ic_home_menu_tips2,
        R.drawable.ic_home_menu_tips3,
        R.drawable.ic_home_menu_tips4,
        R.drawable.ic_home_menu_tips5
    )
    private lateinit var adapter: AboutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMenuClickListeners()
        setupViewPager()
    }

    private fun setupMenuClickListeners() {
        binding.llFood.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
        binding.llMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
        binding.llMine.setOnClickListener {
            startActivity(Intent(this, DishListActivity::class.java))
        }
        binding.llAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        binding.llMe.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
        }
        binding.tvMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewPager() {
        // 初始化 ViewPager 页面资源
        for (picId in picIds) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_aboutvp, null)
            val iv = view.findViewById<ImageView>(R.id.item_aboutvp_iv)
            iv.setImageResource(picId)
            viewList.add(view)

            // 创建指示器
            val pointIv = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 20, 0) }
                setImageResource(R.drawable.point_round_hint)
            }
            pointList.add(pointIv)
            binding.llTipsPoint.addView(pointIv)
        }
        // 设置第一个小圆点为选中状态
        pointList[0].setImageResource(R.drawable.point_round_red)

        // 设置适配器
        adapter = AboutAdapter(viewList)
        binding.vpTips.adapter = adapter

        // 设置 ViewPager 页面监听器
        setViewPagerListener()

        // 启动自动轮播
        handler.sendEmptyMessageDelayed(1, 5000)
    }

    private fun setViewPagerListener() {
        binding.vpTips.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                pointList.forEach { it.setImageResource(R.drawable.point_round_hint) }
                pointList[position % pointList.size].setImageResource(R.drawable.point_round_red)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                val currentItem = binding.vpTips.currentItem
                binding.vpTips.currentItem = currentItem + 1
                sendEmptyMessageDelayed(1, 5000)
            }
        }
    }
}
