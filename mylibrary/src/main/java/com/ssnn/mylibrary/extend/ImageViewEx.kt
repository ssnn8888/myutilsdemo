package com.ssnn.mylibrary.extend

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide


/**
 *@Author: zw
 *@CreateTime: 2022-11-25  10:27
 *@Description:dataBinding布局文件内使用
 */

/**
 * 加载图片
 */
fun ImageView.load(url: String) {
    Glide.with(context).load(url).into(this)
}

/**
 * 加载图片
 */
fun ImageView.load(url: String, defImage: Int) {
    Glide.with(context).load(url).placeholder(defImage).error(defImage).into(this)
}

///**
// * 预加载图片
// */
//fun imagePreLoad(url: String) {
//    Glide.with(UtilsInit.getApp()).load(url).preload()
//}

fun ImageView.loadImage(imageUrl: String) {
    if (imageUrl.isNetUrl()) {
        load(imageUrl)
    } else {
        loadByDrawableName(imageUrl)
    }
}

@SuppressLint("DiscouragedApi")
fun ImageView.loadByDrawableName(imageUrl: String) {
    try {
        val resources = context.resources
        val imageId = resources.getIdentifier(imageUrl, "drawable", context.packageName)
        setImageDrawable(ResourcesCompat.getDrawable(resources, imageId, null))
    } catch (ignored: Exception) {
    }
}