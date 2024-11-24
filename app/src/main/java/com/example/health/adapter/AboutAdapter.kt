package com.example.health.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class AboutAdapter(private val viewList: List<View>) : PagerAdapter() {

    override fun getCount(): Int = Int.MAX_VALUE

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = viewList[position % viewList.size]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = viewList[position % viewList.size]
        container.removeView(view)
    }
}
