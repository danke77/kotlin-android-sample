package com.danke.contacts.medium.extensions

import android.content.Context
import android.graphics.Color
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

fun showDialog(context: Context): MaterialDialog.Builder {
    return MaterialDialog.Builder(context)
            //colors
            .titleColorRes(R.color.dialog_title)
            .contentColorRes(R.color.dialog_content)
            .dividerColorRes(R.color.dialog_divider)
            .backgroundColor(Color.WHITE)
            .positiveColorRes(R.color.dialog_positive)
            .neutralColorRes(R.color.dialog_neutral)
            .negativeColorRes(R.color.dialog_negative)
            .widgetColorRes(R.color.dialog_widget)
            .buttonRippleColorRes(R.color.dialog_button_ripple)
            //gravity
            .titleGravity(GravityEnum.START)
            .contentGravity(GravityEnum.START)
            .buttonsGravity(GravityEnum.START)
}
