package com.danke.contacts.medium.base.activity

import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

abstract class AbsBackActivity : AbsToolbarActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        initToolbarNavigation(R.drawable.ic_back) { onActionBack() }
    }

    override fun toggleOverridePendingTransition() = false

    override fun getOverridePendingTransitionMode() = TransitionMode.END

    protected fun onActionBack() {
        onBackPressed()
    }
}