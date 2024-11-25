package com.example.health.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health.R
import com.example.health.adapter.DishAdapter
import com.example.health.network.DishApi
import com.example.health.network.RetrofitClient
import com.example.health.pojo.MyDish
import com.example.health.pojo.MyDishResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DishAdapter
    private val dishes = mutableListOf<MyDish>() // 用于存储菜品列表

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_list)

        // 获取 SharedPreferences 中的用户 ID
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", -1)

        // 检查用户 ID 是否有效
        if (userId == -1) {
            Toast.makeText(this, "用户未登录或未设置 user_id", Toast.LENGTH_SHORT).show()
            finish() // 结束当前 Activity
            return
        }

        // 初始化 RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DishAdapter(dishes) { dish ->
            // 点击事件处理，跳转到 DishDetailActivity
            val intent = Intent(this, DishDetailActivity::class.java)
            intent.putExtra("name", dish.name)
            intent.putExtra("step", dish.step)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // 发起网络请求，获取菜品列表
        fetchDishes(userId)
    }

    /**
     * 从服务器获取菜品列表
     * @param userId 用户 ID
     */
    private fun fetchDishes(userId: Int) {
        val api = RetrofitClient.instance.create(DishApi::class.java)
        api.getDishesByUserId(userId).enqueue(object : Callback<MyDishResponse> {
            override fun onResponse(call: Call<MyDishResponse>, response: Response<MyDishResponse>) {
                if (response.isSuccessful && response.body()?.code == 1) {
                    response.body()?.data?.let {
                        dishes.clear()
                        dishes.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@DishListActivity, "获取菜品失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyDishResponse>, t: Throwable) {
                Toast.makeText(this@DishListActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
