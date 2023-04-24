package com.ssnn.mylibrary.extends


/**
 *@Author: zw
 *@CreateTime: 2022-11-25  10:27
 *@Description:dataBinding布局文件内使用
 */

/**
 * 加载图片
 */
//fun ImageView.load(url: String) {
//    ImageLoader.Builder().load(url).into(this).show()
//}
//
///**
// * 加载图片
// */
//fun ImageView.load(url: String, defImage: Int) {
//    ImageLoader.Builder().error(defImage).load(url).into(this).show()
//}

///**
// * 预加载图片
// */
//fun imagePreLoad(url: String) {
//    Glide.with(UtilsInit.getApp()).load(url).preload()
//}

//fun ImageView.loadImage(imageUrl: String) {
//    if (imageUrl.isNetUrl()) {
//        load(imageUrl)
//    } else {
//        loadByDrawableName(imageUrl)
//    }
//}
//
//fun ImageView.loadByDrawableName(imageUrl: String) {
//    try {
//        val context = UtilsInit.getApp()
//        val resources = context.resources
//        val imageId = resources.getIdentifier(imageUrl, "drawable", context.packageName)
//        setImageDrawable(ResourcesCompat.getDrawable(resources, imageId, null))
//    } catch (ignored: Exception) {
//    }
//}