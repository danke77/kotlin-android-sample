package com.danke.contacts.medium.component

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class MaskingLayout : FrameLayout {

    private var mTouchSlop: Int = 0
    private var mPrevX: Float = 0.toFloat()
    private var mPrevY: Float = 0.toFloat()
    private var maskingListener: MaskingListener? = null

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    constructor(context: Context) : super(context) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mPrevY = MotionEvent.obtain(ev).y
                mPrevX = MotionEvent.obtain(ev).x
            }

            MotionEvent.ACTION_MOVE -> {
                val eventX = ev.x
                val xDiff = Math.abs(eventX - mPrevX)
                val eventY = ev.y
                val yDiff = Math.abs(eventY - mPrevY)

                if (xDiff > mTouchSlop || yDiff > mTouchSlop) {
                    return false
                }
            }

            MotionEvent.ACTION_UP -> if (null != maskingListener) {
                return maskingListener!!.onClick()
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    interface MaskingListener {
        fun onClick(): Boolean
    }

    fun setMaskingListener(maskingListener: MaskingListener) {
        this.maskingListener = maskingListener
    }

}