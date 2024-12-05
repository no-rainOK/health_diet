package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.health.databinding.ActivityRegisterBinding
import com.example.health.network.RetrofitClient
import com.example.health.network.UserApi
import com.example.health.pojo.response.UserResponse
import com.example.health.pojo.dto.UserRegister
import retrofit2.Call
import retrofit2.Callback

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val userApi: UserApi = RetrofitClient.instance.create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 注册按钮
        binding.btnConfirm.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val rePassword = binding.etPasswordAgain.text.toString().trim()

            // 创建 UserRegister 对象
            val userRegister = UserRegister(username, password, rePassword)

            // 验证输入
            if (!validateInput(userRegister)) return@setOnClickListener

            // 调用注册接口
            registerUser(userRegister)
        }
    }

    private fun validateInput(userRegister: UserRegister): Boolean {
        val (username, password, rePassword) = userRegister

        if (username.isBlank()) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isBlank()) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "密码不能少于6位", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != rePassword) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun registerUser(userRegister: UserRegister) {
        // 调用后端注册接口
        userApi.register(userRegister).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, userResponse: retrofit2.Response<UserResponse>) {
                if (userResponse.isSuccessful) {
                    val registerResponse = userResponse.body()
                    if (registerResponse?.code == 1) { // 注册成功
                        Toast.makeText(this@RegisterActivity, registerResponse.msg ?: "注册成功！", Toast.LENGTH_SHORT).show()

                        // 跳转到登录页面
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, registerResponse?.msg ?: "注册失败", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "服务器错误: ${userResponse.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "网络错误: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}