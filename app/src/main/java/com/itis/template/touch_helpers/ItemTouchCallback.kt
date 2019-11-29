package com.itis.template.touch_helpers

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.film_page.Data
import java.util.Collections.swap


class ItemTouchCallback(var adapter: ItemTouchAdapter) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean = true

    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition,
                target.adapterPosition
        )
        if (viewHolder.adapterPosition < target.adapterPosition) {
            for (i in viewHolder.adapterPosition until target.adapterPosition) {
                swap(Data.films, i, i + 1)
            }
        } else {
            for (i in viewHolder.adapterPosition downTo target.adapterPosition + 1) {
                swap(Data.films, i, i - 1)
            }
        }
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }
}
