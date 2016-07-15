package com.danke.contacts.medium.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

fun showSoftInput(imm: InputMethodManager?, view: View?) {
    imm?.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

fun hideSoftInput(imm: InputMethodManager?, view: View?) {
    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun toggleSoftInput(imm: InputMethodManager?) {
    imm?.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
}