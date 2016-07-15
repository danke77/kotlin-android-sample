package com.danke.contacts.medium.base.activity

import android.support.v7.widget.Toolbar
import android.view.MenuItem

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
interface ToolbarManager {

    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbarNavigation(icon: Int, up: () -> Unit) {
        toolbar.setNavigationIcon(icon)
        toolbar.setNavigationOnClickListener { up() }
    }

    fun initToolbarMenu(menu: Int, click: (MenuItem) -> Boolean) {
        toolbar.inflateMenu(menu)
        toolbar.setOnMenuItemClickListener { click(it) }
    }

}