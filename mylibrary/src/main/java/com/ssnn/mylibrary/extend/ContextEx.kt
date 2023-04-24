package com.ssnn.mylibrary.extend

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager


/**
 *@Author: zw
 *@CreateTime: 2022-11-30  13:56
 *@Description: Context扩展
 */


/**
 * 启动Activity
 */
inline fun <reified T> Context?.startActivity(option: Intent.() -> Unit = {}) {
    this?.startActivity(Intent(this, T::class.java).apply(option))
}

/**
 * 启动Activity
 */
inline fun <reified T> Fragment?.startActivity(option: Intent.() -> Unit = {}) {
    this?.activity?.let {
        startActivity(Intent(it, T::class.java).apply(option))
    }
}


/**
 * 注册本地广播
 */
fun Context?.registerLocalReceiver(
    receiver: BroadcastReceiver,
    action: String,
    function: IntentFilter.() -> Unit = {}
) {
    this?.let {
        LocalBroadcastManager.getInstance(it).registerReceiver(receiver, IntentFilter().apply {
            addAction(action)
            function()
        })
    }
}

/**
 * 注销本地广播
 */
fun Context?.unregisterLocalReceiver(receiver: BroadcastReceiver) {
    this?.let {
        LocalBroadcastManager.getInstance(it).unregisterReceiver(receiver)
    }
}

/**
 * 发送本地广播
 */
fun Context?.sendLocalBroadcast(action: String, function: Intent.() -> Unit = {}) {
    this?.let {
        LocalBroadcastManager.getInstance(it).sendBroadcast(Intent().apply {
            setAction(action)
            function()
        })
    }
}