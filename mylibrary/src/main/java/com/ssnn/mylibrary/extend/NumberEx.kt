package com.ssnn.mylibrary.extend

import com.ssnn.mylibrary.DensityUtils

/**
 * @类描述:
 * @创建人: zw
 * @创建时间: 11:27 2022/11/28
 */

fun Number.dpToPx() = dp

/**
 * 作为单位用 10086.dp 表示的就是 10086dp 长
 */
val Number.dp: Int get() = DensityUtils.dp2px(toFloat())