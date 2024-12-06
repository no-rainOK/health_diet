package com.example.health.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.health.R
import com.example.health.databinding.ActivityDishUpdateBinding
import com.example.health.network.DishApi
import com.example.health.network.RetrofitClient
import com.example.health.pojo.dto.UpdateMyDish
import com.example.health.pojo.response.MyDishResponse
import com.example.health.pojo.response.UpdateDishResponse
import com.example.health.pojo.response.UploadResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DishUpdateActivity : BaseActivity() {

    private lateinit var binding: ActivityDishUpdateBinding
    private var selectedImageUri: Uri? = null
    private val dishApi: DishApi = RetrofitClient.instance.create(DishApi::class.java)

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
            binding.tvDishImage.setImageURI(selectedImageUri)  // 显示选择的图片
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDishUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递的菜品信息
        val id = intent.getIntExtra("id", -1)
        val image = intent.getStringExtra("image") ?: ""
        val name = intent.getStringExtra("name") ?: ""
        val step = intent.getStringExtra("step") ?: ""

        // 显示菜品的当前信息
        binding.etDishName.setText(name)
        binding.etDishStep.setText(step)

        // 显示菜品图片
        Glide.with(this)
            .load(image)
            .transform(CircleCrop())
            .placeholder(R.drawable.placeholder)
            .into(binding.tvDishImage)

        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish() // 返回上一级
        }

        // 提交修改按钮
        binding.btnUpdate.setOnClickListener {
            val updatedName = binding.etDishName.text.toString()
            val updatedStep = binding.etDishStep.text.toString()

            // 更新菜品的 API
            val updatedDish = UpdateMyDish(id, updatedName, updatedStep)
            updateDishInServer(updatedDish)

            // 上传图片（如果选择了图片）
            selectedImageUri?.let {
                uploadImageToServer(id)
            }
        }

        // 上传图片按钮点击事件
        binding.uploadImageButton.setOnClickListener {
            // 使用 registerForActivityResult 选择图片
            pickImageLauncher.launch("image/*")
        }
    }

    // 更新菜品信息
    private fun updateDishInServer(updatedDish: UpdateMyDish) {
        dishApi.updateDish(updatedDish).enqueue(object : Callback<UpdateDishResponse> {
            override fun onResponse(call: Call<UpdateDishResponse>, response: Response<UpdateDishResponse>) {
                if (response.isSuccessful && response.body()?.code == 1) {
                    Toast.makeText(this@DishUpdateActivity, "菜品更新成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DishUpdateActivity, "菜品更新失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UpdateDishResponse>, t: Throwable) {
                Toast.makeText(this@DishUpdateActivity, "菜品更新失败", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // 上传菜品图片
    private fun uploadImageToServer(dishId: Int) {
        selectedImageUri?.let { uri ->
            val file = File(getRealPathFromURI(uri)) // 获取文件路径

            // Create RequestBody for the file
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

//            // Create RequestBody for the dishId
//            val dishIdBody = RequestBody.create("text/plain".toMediaTypeOrNull(), dishId.toString())

            // Make the request
            dishApi.uploadImage(dishId, body).enqueue(object : Callback<UploadResponse> {
                override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                    if (response.isSuccessful && response.body()?.code == 1) {
                        Toast.makeText(this@DishUpdateActivity, "图片上传成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DishUpdateActivity, "图片上传失败", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    Toast.makeText(this@DishUpdateActivity, "图片上传失败", Toast.LENGTH_SHORT).show()
                }
            })
        } ?: run {
            Toast.makeText(this@DishUpdateActivity, "请选择图片", Toast.LENGTH_SHORT).show()
        }
    }

    // 获取实际路径方法
    @SuppressLint("Range")
    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndex("_data")
            if (columnIndex != -1) {
                it.moveToFirst()
                return it.getString(columnIndex)
            }
        }

        // 对于 Android 10 (API 29)及更高版本, 使用 ContentResolver 来获取图片流
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            uri.path ?: ""
        } else {
            ""
        }
    }

    // 用于更新菜品后返回并刷新页面
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val dishId = intent.getIntExtra("id", -1)
            // 刷新菜品数据
            getDishInfo(dishId)
        }
    }

    // 获取菜品信息方法
    private fun getDishInfo(dishId: Int) {
        dishApi.getDishesByUserId(dishId).enqueue(object : Callback<MyDishResponse> {
            override fun onResponse(call: Call<MyDishResponse>, response: Response<MyDishResponse>) {
                if (response.isSuccessful) {
                    // 更新UI显示菜品信息
                    val dish = response.body()
                    // 根据返回的菜品数据更新UI
                    // binding.etDishName.setText(dish?.name) 等等
                } else {
                    Toast.makeText(this@DishUpdateActivity, "菜品信息获取失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyDishResponse>, t: Throwable) {
                Toast.makeText(this@DishUpdateActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
