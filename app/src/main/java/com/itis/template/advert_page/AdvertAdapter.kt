package com.itis.template.advert_page

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdvertAdapter(var adverts: List<Advert>) : RecyclerView.Adapter<AdvertViewHolder>() {

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
        holder.bind(adverts[position])
    }

    override fun getItemCount(): Int = adverts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            AdvertViewHolder.create(parent)
}
