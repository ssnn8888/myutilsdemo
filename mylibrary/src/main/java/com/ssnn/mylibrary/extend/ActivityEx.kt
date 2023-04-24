package com.ssnn.mylibrary.extend

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 *@Author: zw
 *@CreateTime: 2022-12-07  16:14
 *@Description:
 */

/**
 * 隐藏软键盘
 */
fun Activity?.hideKeyboard() {
    this?.let {
        try {
            val view: View? = it.currentFocus
            val mImm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            if (mImm != null && view != null) {
                mImm.hideSoftInputFromWindow(view.windowToken, 0)
                view.clearFocus()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


