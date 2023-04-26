package com.ssnn.mylibrary

import java.io.File
import java.net.URLDecoder

/**
 *@Author: zw
 *@CreateTime: 2023/3/10 12:01
 *@Description:
 */

/**
 * 外部存储cache文件夹
 */
fun getExternalCachePath() = "${UtilsInit.getApp().externalCacheDir}/"
fun getFilePath(name: String) = "${getExternalCachePath()}$name"


/** 删除文件，可以是文件或文件夹
 * @param delFile 要删除的文件夹或文件名
 * @return 删除成功返回true，否则返回false
 */
fun deleteFile(delFile: String, success: () -> Unit = {}, fail: () -> Unit = {}): Boolean {
    val file = File(delFile)
    return if (!file.exists()) {
        fail()
        false
    } else {
        if (file.isFile) deleteSingleFile(delFile, success, fail) else deleteDirectory(delFile, success, fail)
    }
}

/** 删除单个文件
 * @return 单个文件删除成功返回true，否则返回false
 */
fun deleteSingleFile(filePathName: String, success: () -> Unit = {}, fail: () -> Unit = {}): Boolean {
    val file = File(filePathName)
    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
    return if (file.exists() && file.isFile) {
        if (file.delete()) {
            success()
            true
        } else {
            fail()
            false
        }
    } else {
        fail()
        false
    }
}

/** 删除目录及目录下的文件
 * @param filePath 要删除的目录的文件路径
 * @return 目录删除成功返回true，否则返回false
 */
fun deleteDirectory(filePath: String, success: () -> Unit = {}, fail: () -> Unit = {}): Boolean {
    // 如果dir不以文件分隔符结尾，自动添加文件分隔符
    var filePath = filePath
    if (!filePath.endsWith(File.separator)) filePath += File.separator
    val dirFile = File(filePath)
    // 如果dir对应的文件不存在，或者不是一个目录，则退出
    if (!dirFile.exists() || !dirFile.isDirectory) {
        fail()
        return false
    }
    var flag = true
    // 删除文件夹中的所有文件包括子目录
    val files = dirFile.listFiles()
    for (file in files) {
        // 删除子文件
        if (file.isFile) {
            flag = deleteSingleFile(file.absolutePath)
            if (!flag) break
        } else if (file.isDirectory) {
            flag = deleteDirectory(
                file.absolutePath
            )
            if (!flag) break
        }
    }
    if (!flag) {
        fail()
        return false
    }
    // 删除当前目录
    return if (dirFile.delete()) {
        success()
        true
    } else {
        fail()
        false
    }
}

fun isVideo(videoName: String) = videoName.endsWith(".mp4")
        || videoName.endsWith(".flv")
        || videoName.endsWith(".avi")
        || videoName.endsWith(".rm")
        || videoName.endsWith(".rmvb")
        || videoName.endsWith(".wmv")
        || videoName.endsWith(".3gp")


fun isOffice(fileName: String) = fileName.endsWith(".ppt")
        || fileName.endsWith(".pptx")
        || fileName.endsWith(".doc")
        || fileName.endsWith(".docx")
        || fileName.endsWith(".xlsx")
        || fileName.endsWith(".pdf")
        || fileName.endsWith(".xls")


fun getNameFromUrl(url: String): String {
    return try {
        val start = url.indexOfLast { it == '/' }
        val end = url.indexOf('?')
        URLDecoder.decode(url.substring(start + 1, if (end == -1) url.length else end), "utf-8")

    } catch (e: Exception) {
        ""
    }
}

/**
 * 遍历文件夹下所有文件
 */
fun eachFileRecurse(inputFile: File, operation: (File) -> Unit) {
    val files = inputFile.listFiles() ?: return
    for (file in files) {
        if (file.isDirectory) {
            eachFileRecurse(file, operation)
        } else {
            operation(file)
        }
    }
}