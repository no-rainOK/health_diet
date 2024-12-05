package com.example.health.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.health.databinding.ActivityUpdateUserInfoBinding
import com.example.health.network.RetrofitClient
import com.example.health.network.UserApi
import com.example.health.pojo.UpdateUserInfo
import com.example.health.pojo.UpdateUserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateUserInfoActivity : BaseActivity() {

    private lateinit var binding: ActivityUpdateUserInfoBinding
    private val userApi: UserApi = RetrofitClient.instance.create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 使用 ViewBinding 绑定布局
        binding = ActivityUpdateUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递过来的用户信息
        val userInfo = intent.getParcelableExtra<UpdateUserInfo>("userInfo")
        Log.d("UpdateUserInfoActivity", "UserInfo: $userInfo")  // 打印获取到的 userInfo
        if (userInfo == null) {
            Toast.makeText(this, "用户信息加载失败", Toast.LENGTH_SHORT).show()
        }


        if (userInfo != null) {
            // 初始化字段
            binding.editName.setText(userInfo.name)
            binding.editSex.setText(userInfo.sex)
            binding.editEmail.setText(userInfo.email)
            binding.editIntroduction.setText(userInfo.introduction)
        }

        // 设置保存按钮点击事件
        binding.saveButton.setOnClickListener {
            onSaveClicked()
        }
    }

    // 保存按钮点击事件
    private fun onSaveClicked() {
        val updatedName = binding.editName.text.toString()
        val updatedSex = binding.editSex.text.toString()
        val updatedEmail = binding.editEmail.text.toString()
        val updatedIntroduction = binding.editIntroduction.text.toString()

        if (updatedName.isEmpty() || updatedSex.isEmpty() || updatedEmail.isEmpty()) {
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show()
            return
        }

//        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//        val userId = sharedPref.getInt("user_id", -1)

        val userId = intent.getIntExtra("userId", -1)
        Log.d("userId", userId.toString())
        if (userId == -1){
            Toast.makeText(this, "用户id获取失败", Toast.LENGTH_SHORT).show()
        }
        val updateUserInfo = UpdateUserInfo(userId, updatedName, updatedSex, updatedEmail, updatedIntroduction)

        // 调用后端接口更新用户信息
        updateUserInfo(updateUserInfo)
    }

    private fun updateUserInfo(updateUserInfo: UpdateUserInfo) {
        userApi.updateUserInfo(updateUserInfo).enqueue(object : Callback<UpdateUserInfoResponse> {
            override fun onResponse(call: Call<UpdateUserInfoResponse>, response: Response<UpdateUserInfoResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@UpdateUserInfoActivity, "信息更新成功", Toast.LENGTH_SHORT).show()

                    // 设置结果返回给上一个 Activity
                    val resultIntent = Intent()
                    resultIntent.putExtra("updateSuccess", true)
                    setResult(RESULT_OK, resultIntent)


                    finish()  // 返回上一界面
                } else {
                    Toast.makeText(this@UpdateUserInfoActivity, "信息更新失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UpdateUserInfoResponse>, t: Throwable) {
                Toast.makeText(this@UpdateUserInfoActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
