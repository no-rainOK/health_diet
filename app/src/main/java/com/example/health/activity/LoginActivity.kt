package com.example.health.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.health.databinding.ActivityLoginBinding
import com.example.health.network.RetrofitClient
import com.example.health.network.UserApi
import com.example.health.pojo.UserResponse
import com.example.health.pojo.UserLogin
import com.example.health.activity.BaseActivity
import retrofit2.Call
import retrofit2.Callback

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val userApi: UserApi = RetrofitClient.instance.create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userLogin = UserLogin(username, password)

            // 调用后端登录接口
            userApi.login(userLogin).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, userResponse: retrofit2.Response<UserResponse>) {
                    if (userResponse.isSuccessful) {
                        val loginResponse = userResponse.body()
                        if (loginResponse?.code == 1) { // 登录成功
                            val userId = loginResponse?.data?.id?: -1 // 获取用户ID
                            if (userId == -1) {
                                Toast.makeText(this@LoginActivity, "无法获取用户ID", Toast.LENGTH_SHORT).show()
                                return
                            }

                            // 保存用户 ID 到 SharedPreferences
                            val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                            sharedPref.edit().putInt("user_id", userId).apply()

                            // 跳转到主界面
                            Toast.makeText(this@LoginActivity, loginResponse.msg, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, HomeMenuActivity::class.java).apply {
                                putExtra("name", username) // 可选，传递用户名
                            }
                            startActivity(intent)
                            finish()
                        } else { // 登录失败
                            Toast.makeText(this@LoginActivity, loginResponse?.msg, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "服务器错误: ${userResponse.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "网络错误: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
