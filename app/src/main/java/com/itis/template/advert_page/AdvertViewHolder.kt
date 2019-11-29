package com.itis.template.advert_page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.itis.template.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_advert.*

class AdvertViewHolder(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer, ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

    fun bind(advert: Advert) {
        tv_name.text = advert.title
        iv_userpicture.setImageResource(advert.userpicture)
        tv_bio.text = advert.text
        viewPager.adapter = AdvertPagerAdapter(containerView.context, advert.pictures)
        viewPager.addOnPageChangeListener(this)
    }

    companion object {

        fun create(parent: ViewGroup) =
                AdvertViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
                )
    }
}
