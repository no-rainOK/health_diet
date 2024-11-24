package com.example.health.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.health.R
import com.example.health.adapter.MenuAdapter
import com.example.health.bean.FoodBean
import com.example.health.bean.MenuData

class MenuActivity : AppCompatActivity() {
    private var gv: GridView? = null
    private var mDatas: List<FoodBean>? = null
    private var adapter: MenuAdapter? = null

    private var ivBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        gv = findViewById(R.id.gv_menu)
        ivBack = findViewById(R.id.iv_back)
        //        数据源
        mDatas = MenuData.allFoodList
        //        创建适配器对象
        adapter = MenuAdapter(this, mDatas!!)
        //        设置适配器
        gv!!.setAdapter(adapter)
        setListener()
    }

    private fun setListener() {
        ivBack!!.setOnClickListener { finish() }
        gv!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val foodBean = mDatas!![position]
            val intent = Intent(this@MenuActivity, MenuDescActivity::class.java)
            intent.putExtra("food", foodBean)
            startActivity(intent)
        }
    }
}
