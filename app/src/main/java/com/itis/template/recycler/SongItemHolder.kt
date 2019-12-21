package com.itis.template.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_song.*

class SongItemHolder(
        override val containerView: View,
        private val clickLambda: (Song) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(song: Song) {
        tv_title.text = song.name
        val desc = song.author + "\n" + song.album
        tv_desc.text = desc
        iv_preview.setImageResource(song.cover)
        itemView.setOnClickListener {
            clickLambda(song)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Song) -> Unit) =
                SongItemHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false),
                        clickLambda
                )
    }
}
