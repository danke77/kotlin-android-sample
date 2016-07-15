package com.danke.contacts.medium.extensions

import android.os.Environment
import java.io.File

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

private val SD_PATH = Environment.getExternalStorageDirectory().path + File.separator
private val APP_PATH = SD_PATH + "KContacts" + File.separator
private val DATA_PATH = APP_PATH + "data" + File.separator
private val IMAGE_PATH = APP_PATH + "image" + File.separator

fun filePath(fileName: String) = APP_PATH + fileName

fun getFileLength(filePath: String): Long {
    return File(filePath).length()
}

fun delFile(filePath: String?) {
    if (null != filePath) {
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
    }
}

fun isSDCardStateOn(): Boolean = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

fun isSDCardReadOnly(): Boolean = Environment.MEDIA_MOUNTED_READ_ONLY == Environment.getExternalStorageState()