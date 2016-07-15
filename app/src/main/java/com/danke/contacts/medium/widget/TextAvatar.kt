package com.danke.contacts.medium.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.danke.contacts.R
import com.danke.contacts.medium.extensions.color
import com.danke.contacts.medium.extensions.loadFromUrl


/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class TextAvatar : FrameLayout {

    companion object {
        private val TEXT_AVATAR_LENGTH = 2
    }

    private var textSize: Float = 0.toFloat()
    private var textColor: Int = 0
    private var textBackgroundId: Int = 0

    private var avatarImage: ImageView? = null
    private var avatarText: TextView? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(context, attrs)
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {
        getAttrs(context, attrs)
        init(context)
    }

    private fun getAttrs(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TextAvatar)
        textSize = ta.getDimensionPixelSize(R.styleable.TextAvatar_avatarTextSize, 36).toFloat()
        textColor = ta.getColor(R.styleable.TextAvatar_avatarTextColor, color(android.R.color.white))
        textBackgroundId = ta.getResourceId(R.styleable.TextAvatar_avatarTextBackground, R.drawable.text_avatar_empty_background)
        ta.recycle()
    }

    private fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.text_avatar, null)
        avatarImage = view.findViewById(R.id.avatarImage) as BezelImageView
        avatarText = view.findViewById(R.id.avatarText) as TextView

        avatarText!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        avatarText!!.setTextColor(textColor)

        addView(view)
    }

    fun setAvatar(url: String, name: String) {
        val avatarName: String
        if (name.length > TEXT_AVATAR_LENGTH) {
            avatarName = name.substring(name.length - TEXT_AVATAR_LENGTH, name.length)
        } else {
            avatarName = name
        }

        if (TextUtils.isEmpty(url)) {
            setTextAvatar(avatarName)
        } else {
            setImageAvatar(url)
        }
    }

    private fun setTextAvatar(avatarName: String) {
        avatarText?.visibility = VISIBLE
        avatarImage?.visibility = GONE
        avatarText?.text = avatarName
        avatarText?.setBackgroundResource(textBackgroundId)
    }

    private fun setImageAvatar(url: String) {
        avatarText?.visibility = GONE
        avatarImage?.visibility = VISIBLE
        avatarImage?.loadFromUrl(context, url)
    }

}