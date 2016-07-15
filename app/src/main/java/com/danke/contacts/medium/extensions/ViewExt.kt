package com.danke.contacts.medium.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

fun View.color(res: Int): Int = ContextCompat.getColor(ctx, res)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}