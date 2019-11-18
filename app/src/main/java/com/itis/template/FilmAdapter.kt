package com.itis.template

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class FilmAdapter(
        private var dataSource: List<Film>
) : ListAdapter<Film, FilmItemHolder>(object : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Film, newItem: Film): Any? {
        val diffBundle = Bundle()
        if (oldItem.country != newItem.country)
            diffBundle.putString("KEY_COUNTRY", newItem.country)
        if (oldItem.year != newItem.year)
            diffBundle.putString("KEY_YEAR", newItem.country)
        if (oldItem.img != newItem.img)
            diffBundle.putInt("KEY_IMG", newItem.img)
        return if (diffBundle.isEmpty) null else diffBundle
    }
}) {
    override fun onBindViewHolder(holder: FilmItemHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemHolder =
            FilmItemHolder.create(parent)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: FilmItemHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val bundle = payloads[0] as Bundle
            holder.update(bundle)
        }
    }
}
