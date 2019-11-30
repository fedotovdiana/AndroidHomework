package com.itis.template.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(
        private val songs: List<Song>,
        private val clickLambda: (Song) -> Unit
) : RecyclerView.Adapter<SongItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder =
            SongItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) =
            holder.bind(songs[position])
}
