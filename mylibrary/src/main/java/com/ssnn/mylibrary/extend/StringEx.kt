package com.ssnn.mylibrary.extend

import android.text.TextUtils
import org.json.JSONObject
import java.util.*

fun String?.isJson(): Boolean {
    this ?: return false
    try {
        JSONObject(this)
    } catch (e: Exception) {
        return false
    }
    return true
}

/**
 * 是否是网络链接
 *
 * @param url 需要判断的链接
 * @return true http网络链接
 */
fun String?.isNetUrl(): Boolean {
    this?.let { url ->
        return (!TextUtils.isEmpty(url) && (url.lowercase(Locale.getDefault())
            .startsWith("http://") || url.lowercase(Locale.getDefault())
            .startsWith("https://") || url.lowercase(Locale.getDefault())
            .startsWith("www.") || url.lowercase(Locale.getDefault()).startsWith("wap.")))
    } ?: return false
}