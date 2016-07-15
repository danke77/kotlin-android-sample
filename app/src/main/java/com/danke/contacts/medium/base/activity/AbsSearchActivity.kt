package com.danke.contacts.medium.base.activity

import android.app.SearchManager
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danke.contacts.R
import com.danke.contacts.medium.extensions.color

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

abstract class AbsSearchActivity : AbsSwipeBackActivity() {

    private var mSearchView: SearchView? = null
    private var mQuery = ""

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        var query = intent.getStringExtra(SearchManager.QUERY)
        query = if (query == null) "" else query
        mQuery = query

        mSearchView?.setQuery(query, false)

        toolbarTitle = ""

        overridePendingTransition(0, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_search, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null) {
            val view = searchItem.actionView as SearchView
            mSearchView = view

            initSearchView(view)

            if (!TextUtils.isEmpty(mQuery)) {
                view.setQuery(mQuery, false)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.itemId == R.id.menu_search || super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            overridePendingTransition(0, 0)
        }
    }

    private fun initSearchView(searchView: SearchView) {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        //max width match toolbar
        searchView.maxWidth = toolbar.measuredWidth

        searchView.setIconifiedByDefault(true)
        searchView.isIconified = false
        searchView.isSubmitButtonEnabled = true
        // searchView.queryHint = getSearchViewQueryHint();
        // searchView.setQueryRefinementEnabled();

        // search button
        val searchButton = searchView.findViewById(android.support.v7.appcompat.R.id.search_button) as ImageView
        searchButton.setImageResource(R.drawable.ic_search)

        // close button
        val searchCloseButton = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
        searchCloseButton.setImageResource(R.drawable.ic_remove)

        // go button
        val searchGoButton = searchView.findViewById(android.support.v7.appcompat.R.id.search_go_btn) as ImageView
        // searchGoButton.setImageResource(R.drawable.ic_search);
        val layoutParams = LinearLayout.LayoutParams(0, 0)
        searchGoButton.layoutParams = layoutParams

        // custom submit button
        val customSearchButton = TextView(this)
        customSearchButton.setText(R.string.action_search)
        customSearchButton.setTextColor(color(R.color.toolbar_search_text_color))
        customSearchButton.textSize = resources.getDimension(R.dimen.toolbar_menu_size) / resources.displayMetrics.density

        val layoutParams1 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams1.gravity = Gravity.CENTER
        customSearchButton.layoutParams = layoutParams1
        customSearchButton.visibility = View.GONE//用键盘的搜索按钮

        // submit area
        val submitArea = searchView.findViewById(android.support.v7.appcompat.R.id.submit_area) as LinearLayout
        submitArea.addView(customSearchButton)
        submitArea.setPadding(15, 0, 15, 0)
        //submitArea.setBackgroundResource(R.drawable.toolbar_item_background);

        // text color
        val searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAutoComplete.hint = getSearchViewQueryHint()
        searchAutoComplete.textSize = 16f
        searchAutoComplete.setTextColor(color(R.color.toolbar_search_text_color))
        searchAutoComplete.setHintTextColor(color(R.color.toolbar_search_text_hint_color))

        submitArea.setOnClickListener { doActionSearch(searchView, searchView.query.toString()) }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                doActionSearch(searchView, s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                if (searchWhenQueryTextChanged()) {
                    doActionSearch(searchView, s, false)
                }
                return true
            }
        })

        searchView.setOnCloseListener {
            finish()
            false
        }
    }

    private fun doActionSearch(searchView: SearchView, query: String, searchComplete: Boolean = true) {
        if (searchComplete) {
            searchView.clearFocus()
        }
        // toolbar.requestFocus();
        // toolbar.requestFocusFromTouch();
        goSearch(query)
    }

    /**
     * 实时监听搜索字符串变化
     */
    protected open fun searchWhenQueryTextChanged(): Boolean = false

    /**
     * 搜索框的hint
     */
    protected abstract fun getSearchViewQueryHint(): String

    /**
     * 点击搜索后的动作，包括搜索框的搜索和键盘的搜索
     */
    protected abstract fun goSearch(query: String)

    /**
     * 搜索内容为空
     */
    protected fun goEmptySearch() {

    }
}