package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.health.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var time = 3

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                time--
                if (time == 0) {
                    // 跳转页面
                    val intent = Intent(this@MainActivity, GuideActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    binding.tvMain.text = time.toString()
                    sendEmptyMessageDelayed(1, 1000)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler.sendEmptyMessageDelayed(1, 1000)
    }
}
