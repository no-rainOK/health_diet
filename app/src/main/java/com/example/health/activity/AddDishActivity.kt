package com.example.health.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.health.databinding.ActivityAddDishBinding
import com.example.health.network.DishApi
import com.example.health.network.RetrofitClient
import com.example.health.pojo.response.AddDishResponse
import com.example.health.pojo.dto.AddMyDish

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDishActivity : BaseActivity() {

    private lateinit var binding: ActivityAddDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 提交按钮
        binding.tvAdd.setOnClickListener {
            val name = binding.etDishName.text.toString().trim()
            val step = binding.etDishStep.text.toString().trim()

            if (name.isEmpty() || step.isEmpty()) {
                Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addDish(name, step)
        }
    }

    private fun addDish(name: String, step: String) {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", -1)

        if (userId == -1) {
            Toast.makeText(this, "用户未登录", Toast.LENGTH_SHORT).show()
            return
        }

        val api = RetrofitClient.instance.create(DishApi::class.java)
        val addMyDish = AddMyDish(userId, name, step)

        api.addDish(addMyDish).enqueue(object : Callback<AddDishResponse> {
            override fun onResponse(call: Call<AddDishResponse>, response: Response<AddDishResponse>) {
                if (response.isSuccessful && response.body()?.code == 1) {
                    Toast.makeText(this@AddDishActivity, "添加成功", Toast.LENGTH_SHORT).show()
                    finish() // 返回上一级页面
                } else {
                    Toast.makeText(this@AddDishActivity, "添加失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddDishResponse>, t: Throwable) {
                Toast.makeText(this@AddDishActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
