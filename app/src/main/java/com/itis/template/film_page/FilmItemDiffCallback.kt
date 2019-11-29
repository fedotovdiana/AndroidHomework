package com.itis.template.film_page

import androidx.recyclerview.widget.DiffUtil

class FilmItemDiffCallback(var oldList: MutableList<Film>, var newList: MutableList<Film>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}
