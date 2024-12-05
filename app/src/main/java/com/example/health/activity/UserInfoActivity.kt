package com.example.health.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.health.R
import com.example.health.databinding.ActivityUserInfoBinding
import com.example.health.network.RetrofitClient
import com.example.health.network.UserApi
import com.example.health.pojo.UpdateUserInfo
import com.example.health.pojo.UploadAvatarResponse
import com.example.health.pojo.UserInfoResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserInfoActivity : BaseActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private val userApi: UserApi = RetrofitClient.instance.create(UserApi::class.java)

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadAvatar(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get user info
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", -1)
        getUserInfo(userId)

        // Set listeners
        binding.uploadAvatarButton.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.editUserInfoButton.setOnClickListener {
            val userInfo = UpdateUserInfo(
                userId,
                binding.userNameTextView.text.toString(),
                binding.userSexTextView.text.toString(),
                binding.userEmailTextView.text.toString(),
                binding.userIntroductionTextView.text.toString()
            )
            val intent = Intent(this, UpdateUserInfoActivity::class.java).apply {
                putExtra("userInfo", userInfo)
                putExtra("userId", userId)
            }
            Log.d("userId", userId.toString())
            startActivityForResult(intent, 1) // Start the activity and wait for result
        }
    }

    private fun getUserInfo(userId: Int) {
        userApi.getUserInfo(userId).enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(call: Call<UserInfoResponse>, response: Response<UserInfoResponse>) {
                if (response.isSuccessful) {
                    val userInfo = response.body()
                    userInfo?.let {
                        val userData = it.data
                        binding.userNameTextView.text = "${userData.name}"
                        binding.userSexTextView.text = "${userData.sex}"
                        binding.userEmailTextView.text = "${userData.email}"
                        binding.userIntroductionTextView.text = "${userData.introduction ?: "无"}"

                        // Load avatar
                        if (userData.avatar.isNullOrEmpty()) {
                            Glide.with(this@UserInfoActivity)
                                .load(R.drawable.placeholder)
                                .transform(CircleCrop())
                                .into(binding.avatarImageView)
                        } else {
                            Glide.with(this@UserInfoActivity)
                                .load(userData.avatar)
                                .transform(CircleCrop())
                                .into(binding.avatarImageView)
                        }
                    }
                } else {
                    Toast.makeText(this@UserInfoActivity, "用户信息获取失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun uploadAvatar(uri: Uri) {
        val file = File(getRealPathFromURI(uri))
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        userApi.uploadAvatar(1, body).enqueue(object : Callback<UploadAvatarResponse> {
            override fun onResponse(call: Call<UploadAvatarResponse>, response: Response<UploadAvatarResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@UserInfoActivity, "头像上传成功", Toast.LENGTH_SHORT).show()
                    // Refresh user info after avatar update
                    val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    val userId = sharedPref.getInt("user_id", -1)
                    getUserInfo(userId)  // Refresh the data
                } else {
                    Toast.makeText(this@UserInfoActivity, "头像上传失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UploadAvatarResponse>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, "头像上传失败", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("Range")
    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex("_data")
        val path = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return path ?: ""
    }

    // Override onActivityResult to refresh user info after editing user info
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val userId = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getInt("user_id", -1)
            getUserInfo(userId)  // Refresh after editing
        }
    }
}
