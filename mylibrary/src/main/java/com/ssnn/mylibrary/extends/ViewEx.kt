package com.ssnn.mylibrary.extends

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup

/**
 *@Author: zw
 *@CreateTime: 2023/3/3 16:20
 *@Description:
 */


private val mHints = LongArray(4)

/**
 * 多次点击
 */
fun View.setOnMoreClickListener(onClick: () -> Unit) {
    setOnClickListener {
        //将mHints数组内的所有元素左移一个位置
        System.arraycopy(mHints, 1, mHints, 0, mHints.size - 1);
        //获得当前系统已经启动的时间
        mHints[mHints.size - 1] = SystemClock.uptimeMillis();
        if (SystemClock.uptimeMillis() - mHints[0] <= 500) {
            onClick()
        }
    }
}


fun View.isVisibility(isVisibility: Boolean) {
    this.visibility = if (isVisibility) View.VISIBLE else View.GONE
}


fun View.setMarginBottom(size: Int) {
    setMargin {
        it.bottomMargin = size
    }
}

fun View.setMarginTop(size: Int) {
    setMargin {
        it.topMargin = size
    }
}

fun View.setMarginLeft(size: Int) {
    setMargin {
        it.leftMargin = size
    }
}

fun View.setMarginRight(size: Int) {
    setMargin {
        it.rightMargin = size
    }
}

fun View.setHeight(size: Int) {
    val linearParams = layoutParams as ViewGroup.LayoutParams
    linearParams.height = size
    layoutParams = linearParams
}

fun View.setWidth(size: Int) {
    val linearParams = layoutParams as ViewGroup.LayoutParams
    linearParams.width = size
    layoutParams = linearParams
}

inline fun View.setMargin(option: (ViewGroup.MarginLayoutParams) -> Unit) {
    val lp = this.layoutParams as ViewGroup.LayoutParams
    if (lp is ViewGroup.MarginLayoutParams) {
        option(lp)
        this.layoutParams = lp
    } else {
        throw IllegalArgumentException("该View无法设置Margin")
    }
}
