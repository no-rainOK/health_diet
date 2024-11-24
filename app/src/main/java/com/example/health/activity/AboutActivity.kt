package com.example.health.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.health.R
import com.example.health.adapter.AboutAdapter
import com.example.health.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    private val viewList = mutableListOf<View>() // ViewPager的数据源
    private val picIds = intArrayOf(
        R.drawable.ic_about1, R.drawable.ic_about2, R.drawable.ic_about3,
        R.drawable.ic_about4, R.drawable.ic_about5
    )
    private val pointList = mutableListOf<ImageView>() // 存放显示器小点点的集合
    private lateinit var adapter: AboutAdapter

    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                val currentItem = binding.vpTips.currentItem
                binding.vpTips.currentItem = currentItem + 1
                sendEmptyMessageDelayed(1, 5000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupViewPager()
        setVPListener()
    }

    private fun setupListeners() {
        binding.ivBack.setOnClickListener { finish() }

        binding.llShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "保持健康的饮食至关重要。通过了解营养和热量，选择合适食物，提升健康。想了解更多？快来下载饮食指南app吧！"
                )
            }
            startActivity(Intent.createChooser(intent, "饮食指南分享"))
        }
    }

    private fun setupViewPager() {
        // 初始化 ViewPager 的页面信息
        picIds.forEach { picId ->
            val view = layoutInflater.inflate(R.layout.item_aboutvp, null)
            val iv = view.findViewById<ImageView>(R.id.item_aboutvp_iv)
            iv.setImageResource(picId)
            viewList.add(view)

            // 创建指示器内容
            val pointIv = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 20, 0)
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(R.drawable.point_round_hint)
            }
            pointList.add(pointIv)
            binding.llTipsPoint.addView(pointIv)
        }
        pointList[0].setImageResource(R.drawable.point_round_red)

        // 创建适配器对象
        adapter = AboutAdapter(viewList)
        binding.vpTips.adapter = adapter

        // 发送切换页面消息
        handler.sendEmptyMessageDelayed(1, 5000)
    }

    private fun setVPListener() {
        binding.vpTips.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                pointList.forEach { it.setImageResource(R.drawable.point_round_hint) }
                pointList[position % pointList.size].setImageResource(R.drawable.point_round_red)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
