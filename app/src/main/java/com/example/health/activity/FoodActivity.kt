package com.example.health.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.health.adapter.FoodAdapter
import com.example.health.bean.FoodBean
import com.example.health.sqlite.DatabaseHelper
import com.example.health.databinding.ActivityFoodBinding

class FoodActivity : BaseActivity() {

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
        // 从数据库中获取数据
        val dbHelper = DatabaseHelper(this)
        allFoodList = dbHelper.getAllFoodData()
        Log.d("fooddata", allFoodList.toString())

        // 初始化适配器
        adapter = FoodAdapter(this, mDatas)
        binding.lvInfo.adapter = adapter
    }

    private fun setupData() {
        // 初始化数据
        mDatas.clear()
        mDatas.addAll(allFoodList)
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
            val filteredList = allFoodList.filter { it.name!!.contains(msg) }
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
