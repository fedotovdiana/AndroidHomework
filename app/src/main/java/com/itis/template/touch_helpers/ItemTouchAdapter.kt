package com.itis.template.touch_helpers

interface ItemTouchAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}
