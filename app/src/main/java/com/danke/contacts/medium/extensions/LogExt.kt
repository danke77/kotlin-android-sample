package com.danke.contacts.medium.extensions

import android.text.TextUtils
import android.util.Log
import com.danke.contacts.BuildConfig

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

val debug: Boolean = BuildConfig.DEBUG
val TAG: String = "KContacts"

fun logv(tag: String, msg: String) {
    if (debug && !TextUtils.isEmpty(msg)) {
        Log.v(tag, msg)
    }
}

fun logv(msg: String) {
    logv(TAG, msg)
}

fun logd(tag: String, msg: String) {
    if (debug && !TextUtils.isEmpty(msg)) {
        Log.d(tag, msg)
    }
}

fun logd(msg: String) {
    logd(TAG, msg)
}

fun logw(tag: String, msg: String) {
    if (debug && !TextUtils.isEmpty(msg)) {
        Log.w(tag, msg)
    }
}

fun logw(msg: String) {
    logw(TAG, msg)
}

fun loge(tag: String, msg: String) {
    if (debug && !TextUtils.isEmpty(msg)) {
        Log.e(tag, msg)
    }
}

fun loge(msg: String) {
    loge(TAG, msg)
}