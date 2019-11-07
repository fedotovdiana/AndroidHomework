package com.itis.template

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(
        private var dataSource: List<Film>,
        private val clickLambda: (String, String, String, Int) -> Unit
) : RecyclerView.Adapter<FilmItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemHolder =
            FilmItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: FilmItemHolder, position: Int) =
            holder.bind(dataSource[position])
}
