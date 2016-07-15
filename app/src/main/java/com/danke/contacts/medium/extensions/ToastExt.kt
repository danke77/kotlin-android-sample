package com.danke.contacts.medium.extensions

import android.content.Context
import android.widget.Toast

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

private var toast: Toast? = null

fun showToast(context: Context?, res: Int) {
    if (null == context) {
        return
    }

    toast = Toast.makeText(context, res, Toast.LENGTH_SHORT)
    toast!!.show()
}

fun showToast(context: Context?, res: String) {
    if (null == context) {
        return
    }

    toast = Toast.makeText(context, res, Toast.LENGTH_SHORT)
    toast!!.show()
}

fun showLongToast(context: Context?, res: Int) {
    if (null == context) {
        return
    }

    toast = Toast.makeText(context, res, Toast.LENGTH_LONG)
    toast!!.show()
}

fun showLongToast(context: Context?, res: String) {
    if (null == context) {
        return
    }

    toast = Toast.makeText(context, res, Toast.LENGTH_LONG)
    toast!!.show()
}
