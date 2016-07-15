package com.danke.contacts.medium.extensions

import android.app.Activity
import org.jetbrains.anko.internals.AnkoInternals

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

inline fun <reified T : Activity> Activity.startActivity(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivity(this, T::class.java, params)
}
