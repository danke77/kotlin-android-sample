package com.danke.contacts.medium.extensions

import android.os.Parcel
import android.os.Parcelable

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }