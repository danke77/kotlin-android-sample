package com.danke.contacts.business.search.ui

import android.os.Bundle
import com.danke.contacts.R
import com.danke.contacts.medium.base.activity.AbsSearchActivity

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

class SearchActivity : AbsSearchActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        toolbarTitle = getString(R.string.action_search)
    }

    override fun getSearchViewQueryHint(): String = getString(R.string.action_search)

    override fun goSearch(query: String) {
        //TODO
    }

}