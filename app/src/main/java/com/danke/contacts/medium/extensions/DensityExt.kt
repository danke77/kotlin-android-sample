package com.danke.contacts.medium.extensions

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

fun dp2px(context: Context, dpValue: Double): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun px2dp(context: Context, pxValue: Double): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun px2sp(context: Context, pxValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

fun sp2px(context: Context, spValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

fun getScreenWidth(activity: Activity): Int {
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun getScreenHeight(activity: Activity): Int {
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}