package com.ssnn.mylibrary.extends

import android.os.SystemClock
import android.view.View

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

