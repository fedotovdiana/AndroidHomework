package com.itis.template

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*

class NoteViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(note: Note, clickListener: (Note) -> Unit) {
        tv_title.text = note.title
        tv_desc.text = note.description
        itemView.setOnClickListener { clickListener(note) }
    }
}
