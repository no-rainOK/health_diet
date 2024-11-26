package com.example.health.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health.databinding.ActivityDishUpdateBinding
import com.example.health.network.DishApi
import com.example.health.network.RetrofitClient
import com.example.health.pojo.UpdateDishResponse
import com.example.health.pojo.UpdateMyDish
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDishUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递的菜品信息
        val id = intent.getIntExtra("id", -1)
        val name = intent.getStringExtra("name") ?: ""
        val step = intent.getStringExtra("step") ?: ""

        // 显示菜品的当前信息
        binding.etDishName.setText(name)
        binding.etDishStep.setText(step)

        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish() // 返回上一级
        }

        // 提交修改按钮
        binding.btnUpdate.setOnClickListener {
            val updatedName = binding.etDishName.text.toString()
            val updatedStep = binding.etDishStep.text.toString()

            // 调用更新菜品的 API
            val updatedDish = UpdateMyDish(id, updatedName, updatedStep)
            updateDishInServer(updatedDish)
        }
    }

    private fun updateDishInServer(updatedDish: UpdateMyDish) {
        val api = RetrofitClient.instance.create(DishApi::class.java)

        api.updateDish(updatedDish).enqueue(object : Callback<UpdateDishResponse> {
            override fun onResponse(call: Call<UpdateDishResponse>, response: Response<UpdateDishResponse>) {
                if (response.isSuccessful && response.body()?.code == 1) {
                    Toast.makeText(this@DishUpdateActivity, "修改成功", Toast.LENGTH_SHORT).show()
                    finish() // 修改成功后返回
                } else {
                    Toast.makeText(this@DishUpdateActivity, "修改失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UpdateDishResponse>, t: Throwable) {
                Toast.makeText(this@DishUpdateActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
