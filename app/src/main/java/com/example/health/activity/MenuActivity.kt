package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ImageView
import com.example.health.R
import com.example.health.adapter.MenuAdapter
import com.example.health.bean.MenuBean
import com.example.health.sqlite.DatabaseHelper

class MenuActivity : BaseActivity() {
    private var gv: GridView? = null
    private var mDatas: List<MenuBean>? = null
    private var adapter: MenuAdapter? = null

    private var ivBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        gv = findViewById(R.id.gv_menu)
        ivBack = findViewById(R.id.iv_back)

        // 从数据库获取 menu 数据
        val dbHelper = DatabaseHelper(this)
        mDatas = dbHelper.getAllMenuData()

        // 创建适配器
        adapter = MenuAdapter(this, mDatas!!)
        // 设置适配器
        gv!!.adapter = adapter
        setListener()
    }

    private fun setListener() {
        ivBack!!.setOnClickListener { finish() }
        gv!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val menuBean = mDatas!![position]
            val intent = Intent(this@MenuActivity, MenuDescActivity::class.java)
            intent.putExtra("menu", menuBean)
            startActivity(intent)
        }
    }
}
