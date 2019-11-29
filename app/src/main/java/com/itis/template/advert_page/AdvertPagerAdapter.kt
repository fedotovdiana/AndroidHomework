package com.itis.template.advert_page

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter

class AdvertPagerAdapter(var context: Context, var pictures: List<Int>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = pictures.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater
                .from(context)
                .inflate(com.itis.template.R.layout.item_view_pager, container, false) as ViewGroup
        val iv = layout.findViewById<ImageView>(com.itis.template.R.id.iv_view_pager)
        iv.setImageResource(pictures[position])
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
