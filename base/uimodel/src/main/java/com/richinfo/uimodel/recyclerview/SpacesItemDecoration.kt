package com.richinfo.uimodel.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @ClassName SpacesItemDecoration
 * @Author zjw
 * @Date 2021/3/17 23:36
 * 简介：RecyclerView 间隔
 */
class SpacesItemDecoration(private val space: HashMap<String, Int>) :
    RecyclerView.ItemDecoration() {

    companion object {
        const val LEFT = "left"
        const val TOP = "top"
        const val RIGHT = "right"
        const val BOTTOM = "bottom"
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space[LEFT]!!
        outRect.top = space[TOP]!!
        outRect.right = space[RIGHT]!!
        outRect.bottom = space[BOTTOM]!!
    }


}