package com.ssnn.mylibrary.extend

import org.json.JSONArray
import org.json.JSONObject

/**
 * @类描述:
 * @创建人: zw
 * @创建时间: 14:16 2022/12/6
 */
inline fun JSONArray?.forEach(action: (JSONObject) -> Unit) {
    this?.apply {
        for (i in 0 until length()) {
            action(optJSONObject(i) ?: continue)
        }
    }
}

inline fun <T> JSONArray.toList(action: (JSONObject) -> T): List<T> {
    val result = mutableListOf<T>()
    for (i in 0 until length()) {
        result.add(action(optJSONObject(i) ?: continue))
    }
    return result
}

fun JSONArray?.getJSONObject(index: Int): JSONObject {
    this ?: return JSONObject()
    return if (index < this.length()) {
        (this[index] as JSONObject)
    } else {
        JSONObject()
    }
}