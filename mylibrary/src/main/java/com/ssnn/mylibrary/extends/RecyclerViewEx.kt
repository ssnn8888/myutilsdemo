package com.ssnn.mylibrary.extends

import androidx.recyclerview.widget.RecyclerView
import com.ssnn.mylibrary.widget.RecyclerViewDivider


/**
 * @类描述:
 * @创建人: zw
 * @创建时间: 16:21 2022/11/28
 */
fun RecyclerView.addItemVerticalDivider(dividerSize: Int, dividerColor: Int = 0x00ffffff) {
    val recyclerViewDividerV = RecyclerViewDivider(RecyclerViewDivider.VERTICAL, dividerSize, dividerColor)
    addItemDecoration(recyclerViewDividerV)
}

fun RecyclerView.addItemHorizontalDivider(dividerSize: Int, dividerColor: Int = 0x00ffffff) {
    val recyclerViewDividerV = RecyclerViewDivider(RecyclerViewDivider.HORIZONTAL, dividerSize, dividerColor)
    addItemDecoration(recyclerViewDividerV)
}
