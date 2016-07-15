package com.danke.contacts.medium.extensions

import android.content.pm.PackageInfo
import com.danke.contacts.BuildConfig
import com.danke.contacts.business.KContactsApp

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
private var packInfo: PackageInfo? = null

private fun getPackageInfo() {
    try {
        val packageManager = KContactsApp.instance.packageManager
        packInfo = packageManager.getPackageInfo(KContactsApp.instance.packageName, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getVersionName(): String = BuildConfig.VERSION_NAME

fun getVersionCode(): Int = BuildConfig.VERSION_CODE

fun getPackageName(): String = BuildConfig.APPLICATION_ID
