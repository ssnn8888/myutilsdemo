package com.ssnn.mylibrary

import android.annotation.SuppressLint
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*


/**
 *@Author: zw
 *@CreateTime: 2022-12-06  10:42
 *@Description:时间工具类
 */

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun transToTimeStamp(date: String, format: String = "yyyy-MM-DD HH:mm:ss"): Long {
        return SimpleDateFormat(format).parse(date, ParsePosition(0))?.time ?: 0
    }

    @SuppressLint("SimpleDateFormat")
    fun transToString(time: Long, format: String = "yyyy-MM-DD HH:mm:ss"): String {
        return SimpleDateFormat(format).format(time)
    }


    /**
     * 判断日期是否为当天
     *
     * @param timestamp 时间戳
     * @return 0:当天，-1:昨天，1:明天
     */
    fun isToday(timestamp: Long): Int {
        val before = getDayTimestamp(timestamp)
        val now = getDayTimestamp(System.currentTimeMillis())
        return -((now - before) / (24 * 60 * 60 * 1000)).toInt()
    }

    /**
     * 从时间戳获取当天时间戳
     */
    fun getDayTimestamp(timestamp: Long) = Calendar.getInstance(TimeZone.getTimeZone("GMT+8")).apply {
        time = Date(timestamp)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }.timeInMillis


    /**
     * 显示日期时间
     */
    fun showRealityDate(timestamp: Long) = when (isToday(timestamp)) {
        0 -> transToString(timestamp, "HH:mm")
        -1 -> transToString(timestamp, "昨天 HH:mm")
        else -> transToString(timestamp, "MM.dd HH:mm")
    }

    /**
     * 显示日期时间
     */
    fun showRealityDate(timestamp: String) = try {
        showRealityDate(timestamp.toLong())
    } catch (e: Exception) {
        ""
    }

}


