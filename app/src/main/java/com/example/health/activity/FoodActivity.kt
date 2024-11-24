package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health.R
import com.example.health.adapter.FoodAdapter
import com.example.health.bean.FoodBean
import com.example.health.bean.FoodData
import com.example.health.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding
    private lateinit var adapter: FoodAdapter

    // 数据源
    private val mDatas = mutableListOf<FoodBean>()
    private lateinit var allFoodList: List<FoodBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setupData()
        setupListeners()
    }

    private fun initView() {
        allFoodList = FoodData.allFoodList
        mDatas.addAll(allFoodList)

        // 设置适配器
        adapter = FoodAdapter(this, mDatas)
        binding.lvInfo.adapter = adapter
    }

    private fun setupData() {
        // 初始化数据
        mDatas.clear()
        mDatas.addAll(FoodData.allFoodList)
        adapter.notifyDataSetChanged()
    }

    private fun setupListeners() {
        binding.ivBack.setOnClickListener { finish() }

        binding.ivFlush.setOnClickListener {
            // 刷新功能
            binding.etSearch.setText("")
            mDatas.clear()
            mDatas.addAll(allFoodList)
            adapter.notifyDataSetChanged()
        }

        binding.ivSearch.setOnClickListener {
            // 搜索功能
            val msg = binding.etSearch.text.toString().trim()
            if (TextUtils.isEmpty(msg)) {
                Toast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val filteredList = allFoodList.filter { it.title!!.contains(msg) }
            mDatas.clear()
            mDatas.addAll(filteredList)
            adapter.notifyDataSetChanged()
        }

        binding.lvInfo.setOnItemClickListener { _, _, position, _ ->
            val foodBean = mDatas[position]
            val intent = Intent(this, FoodDescActivity::class.java).apply {
                putExtra("food", foodBean)
            }
            startActivity(intent)
        }
    }
}
