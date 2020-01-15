package com.itis.template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
        val data: List<Note>,
        val clickListener: (Note) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
            holder.bind(data[position], clickListener)
}
