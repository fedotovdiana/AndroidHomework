package com.itis.template.film_page

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.touch_helpers.ItemTouchAdapter

class FilmAdapter(
        private var dataSource: MutableList<Film>,
        private val clickLambda: (Film) -> Unit
) : RecyclerView.Adapter<FilmItemHolder>(), ItemTouchAdapter {

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemDismiss(position: Int) {
        val newList = dataSource.toMutableList()
        newList.removeAt(position)
        update(newList)
        Data.films = dataSource.toMutableList()
    }

    override fun onBindViewHolder(holder: FilmItemHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FilmItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: FilmItemHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val bundle = payloads[0] as Bundle
            holder.update(bundle)
        }
    }

    fun update(newList: MutableList<Film>) {
        val callback = FilmItemDiffCallback(dataSource, newList)
        val diffResult = DiffUtil.calculateDiff(callback, true)
        diffResult.dispatchUpdatesTo(this)
        dataSource.clear()
        dataSource.addAll(newList)
    }
}
