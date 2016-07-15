package com.danke.contacts.medium.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

fun actionMakePhoneCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel:" + phoneNumber)
    try {
        context.startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
    }
}

fun actionSendSms(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("sms:" + phoneNumber)
    try {
        context.startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
    }
}

fun actionSendEmail(context: Context, emailAddress: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "message/rfc882"
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
    intent.putExtra(Intent.EXTRA_CC, arrayOf(""))
    intent.putExtra(Intent.EXTRA_BCC, arrayOf(""))
    intent.putExtra(Intent.EXTRA_TEXT, "")
    intent.putExtra(Intent.EXTRA_SUBJECT, "")
    try {
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_email)))
    } catch (ex: android.content.ActivityNotFoundException) {
        showToast(context, R.string.no_email_client)
    }
}