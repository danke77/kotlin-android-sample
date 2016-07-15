package com.danke.contacts.medium.base.activity

import android.os.Bundle
import android.view.View
import com.danke.contacts.medium.component.swipe.SwipeBackActivityBase
import com.danke.contacts.medium.component.swipe.SwipeBackActivityHelper
import com.danke.contacts.medium.component.swipe.SwipeBackUtils

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

abstract class AbsSwipeBackActivity : AbsBackActivity(), SwipeBackActivityBase {

    private var mHelper: SwipeBackActivityHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHelper = SwipeBackActivityHelper(this)
        mHelper?.onActivityCreate()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper?.onPostCreate()
    }

    override fun findViewById(id: Int): View? {
        val view = super.findViewById(id)
        if (view == null && mHelper != null) {
            return mHelper?.findViewById(id)
        }

        return super.findViewById(id)
    }

    override fun getSwipeBackLayout() = mHelper?.swipeBackLayout

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout?.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        SwipeBackUtils.convertActivityFromTranslucent(this)
        swipeBackLayout?.scrollToFinishActivity()
    }

}