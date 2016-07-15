package com.danke.contacts.business.user.ui

import android.os.Bundle
import com.danke.contacts.R
import com.danke.contacts.medium.base.activity.AbsSearchActivity

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

class UserSearchActivity : AbsSearchActivity() {

    private var mUserListFragment: UserListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        toolbarTitle = getString(R.string.action_search)

        mUserListFragment = UserListFragment.newInstance()
        fragmentManager.beginTransaction().
                replace(R.id.common_fragment_container, mUserListFragment).
                commitAllowingStateLoss()
    }

    override fun getSearchViewQueryHint(): String = getString(R.string.search_user_hint)

    override fun searchWhenQueryTextChanged() = true

    override fun goSearch(query: String) {
        mUserListFragment?.requestUserInfoList(query)
    }

}