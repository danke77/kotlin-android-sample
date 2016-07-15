package com.danke.contacts.medium.base.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import com.danke.contacts.R
import com.danke.contacts.medium.extensions.color
import org.jetbrains.anko.find

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

abstract class AbsToolbarActivity : AbsActivity(), ToolbarManager {

    open fun isStatusBarTransparent(): Boolean = false

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && isStatusBarTransparent()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color(android.R.color.transparent)
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setSupportActionBar(toolbar)
    }

}