package com.example.health.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.health.adapter.DishAdapter
import com.example.health.databinding.ActivityDishListBinding
import com.example.health.network.DishApi
import com.example.health.network.RetrofitClient
import com.example.health.pojo.response.DeleteDishResponse
import com.example.health.pojo.dto.MyDish
import com.example.health.pojo.response.MyDishResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishListActivity : BaseActivity() {

    private lateinit var binding: ActivityDishListBinding
    private lateinit var adapter: DishAdapter
    private val dishes = mutableListOf<MyDish>() // 用于存储菜品列表

    override fun onResume() {
        super.onResume()

        // 获取 SharedPreferences 中的用户 ID
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", -1)

        if (userId != -1) {
            fetchDishes(userId) // 重新获取菜品列表
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 使用 View Binding
        binding = ActivityDishListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 添加按钮
        binding.tvAdd.setOnClickListener {
            val intent = Intent(this, AddDishActivity::class.java)
            startActivity(intent)
        }

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
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DishAdapter(dishes, { dish ->
            // 单击事件：跳转到菜品详情
            val intent = Intent(this, DishDetailActivity::class.java)
            intent.putExtra("id", dish.id)
            intent.putExtra("name", dish.name)
            intent.putExtra("step", dish.step)
            startActivity(intent)
        }, { dish ->
            // 长按事件：删除菜品
            showDeleteConfirmationDialog(dish)
        })
        binding.recyclerView.adapter = adapter


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
                        dishes.clear() // 清空旧数据
                        dishes.addAll(it) // 添加新数据
                        adapter.notifyDataSetChanged() // 通知适配器更新
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


    private fun showDeleteConfirmationDialog(dish: MyDish) {
        AlertDialog.Builder(this)
            .setTitle("删除菜品")
            .setMessage("确定要删除菜品 \"${dish.name}\" 吗？")
            .setPositiveButton("删除") { _, _ ->
                deleteDish(dish.id) // 确认删除时调用删除方法
            }
            .setNegativeButton("取消", null)
            .show()
    }


    private fun deleteDish(dishId: Int) {
        val api = RetrofitClient.instance.create(DishApi::class.java)
        api.deleteDish(dishId).enqueue(object : Callback<DeleteDishResponse> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<DeleteDishResponse>, response: Response<DeleteDishResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@DishListActivity, "菜品删除成功", Toast.LENGTH_SHORT).show()
                    // 更新列表，移除删除的菜品
                    dishes.removeIf { it.id == dishId }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@DishListActivity, "删除失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DeleteDishResponse>, t: Throwable) {
                Toast.makeText(this@DishListActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
