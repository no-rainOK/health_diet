package com.example.health.activity

import android.app.AlertDialog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health.adapter.MineAdapter
import com.example.health.bean.MenuBean
import com.example.health.databinding.ActivityMineBinding
import com.example.health.sqlite.MenuBusiness

class MineActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_UPDATE_MINE = 1
    }

    private lateinit var binding: ActivityMineBinding
    private lateinit var adapter: MineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        initializeData()
    }

    private fun setupListeners() {
        // 返回按钮点击事件
        binding.ivBack.setOnClickListener { finish() }

        // 添加按钮点击事件
        binding.tvAdd.setOnClickListener {
            val intent = Intent(this, MineAddActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_UPDATE_MINE)
        }

        // ListView 项目点击事件
        binding.lvMenu.setOnItemClickListener { adapterView, _, position, _ ->
            val menuBean = adapterView.getItemAtPosition(position) as MenuBean
            showActionDialog(menuBean)
        }
    }

    private fun initializeData() {
        val menuList = MenuBusiness.all
        adapter = MineAdapter(this, menuList)
        binding.lvMenu.adapter = adapter
    }

    private fun showActionDialog(menuBean: MenuBean) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("请选择操作")
            .setNegativeButton("删除") { _, _ ->
                val result = MenuBusiness.deleteOne(menuBean)
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                refreshListView()
            }
            .setPositiveButton("修改") { _, _ ->
                val intent = Intent(this, MenuUpdateActivity::class.java).apply {
                    putExtra("id", menuBean.id)
                    putExtra("title", menuBean.title)
                    putExtra("content", menuBean.content)
                }
                startActivityForResult(intent, REQUEST_CODE_UPDATE_MINE)
            }
            .create()
        dialog.show()
    }

    private fun refreshListView() {
        val updatedList = MenuBusiness.all
        adapter.setData(updatedList)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_UPDATE_MINE && resultCode == RESULT_OK) {
            refreshListView()
        }
    }
}
