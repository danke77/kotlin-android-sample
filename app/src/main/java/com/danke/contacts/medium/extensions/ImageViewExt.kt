package com.danke.contacts.medium.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

val NO_PLACE_HOLDER = -1

fun ImageView.loadFromUrl(context: Context, url: String) {
    loadFromUrl(context, url, R.drawable.text_avatar_empty_background)
}

fun ImageView.loadFromUrl(context: Context, url: String, placeholderResId: Int) {
    val requestManager = Glide.with(context)
    val drawableTypeRequest = requestManager.load(url)
    if (NO_PLACE_HOLDER == placeholderResId) {
        drawableTypeRequest.placeholder(null)
    } else {
        drawableTypeRequest.placeholder(placeholderResId)
    }
    drawableTypeRequest.centerCrop()
            .crossFade()
            .into(this)
}
