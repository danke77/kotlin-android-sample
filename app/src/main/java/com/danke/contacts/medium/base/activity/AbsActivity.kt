package com.danke.contacts.medium.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
abstract class AbsActivity : AppCompatActivity() {

    private var mProgressView: View? = null
    private var mRootView: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (toggleOverridePendingTransition()) {
            when (getOverridePendingTransitionMode()) {
                TransitionMode.START -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
                TransitionMode.END -> overridePendingTransition(R.anim.right_in, R.anim.right_out)
                TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
                TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out)
                TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
                TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                TransitionMode.NONE -> {
                }
            }
        }

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRootView = null
        mProgressView = null
    }

    override fun finish() {
        super.finish()

        if (toggleOverridePendingTransition()) {
            when (getOverridePendingTransitionMode()) {
                TransitionMode.START -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
                TransitionMode.END -> overridePendingTransition(R.anim.right_in, R.anim.right_out)
                TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
                TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out)
                TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
                TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                TransitionMode.NONE -> {
                }
            }
        }
    }

    fun showProgressBar() {
        if (mProgressView == null) {
            mRootView = window.decorView as ViewGroup
            mProgressView = LayoutInflater.from(this).inflate(R.layout.progress_layout, null)

            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.gravity = Gravity.CENTER
            mRootView?.addView(mProgressView, layoutParams)
        }
    }

    fun dismissProgressBar() {
        if (mRootView != null && mProgressView != null) {
            mRootView!!.removeView(mProgressView)
        }

        mProgressView = null
        mRootView = null
    }

    /**
     * overridePendingTransition mode
     */
    enum class TransitionMode {
        START, END, TOP, BOTTOM, SCALE, FADE, NONE
    }

    /**
     * toggle overridePendingTransition
     */
    protected abstract fun toggleOverridePendingTransition(): Boolean

    /**
     * get the overridePendingTransition mode
     */
    protected abstract fun getOverridePendingTransitionMode(): TransitionMode

}