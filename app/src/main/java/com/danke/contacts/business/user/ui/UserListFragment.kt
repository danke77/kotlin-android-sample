package com.danke.contacts.business.user.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danke.contacts.R
import com.danke.contacts.business.KContactsApp
import com.danke.contacts.business.user.adapter.DefaultUserInfoAdapter
import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.base.fragment.AbsFragment
import com.danke.contacts.medium.component.recycler.WrapContentLinearLayoutManager
import com.youzan.titan.divider.HorizontalDivider
import kotlinx.android.synthetic.main.fragment_user_list.*
import java.util.*

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class UserListFragment private constructor() : AbsFragment() {

    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mDefaultUserInfoAdapter: DefaultUserInfoAdapter? = null

    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = WrapContentLinearLayoutManager(mAttachActivity)
        userInfoRecyclerView.layoutManager = mLinearLayoutManager
        userInfoRecyclerView.addItemDecoration(HorizontalDivider.Builder(mAttachActivity).colorResId(R.color.item_separate_line).build())

        mDefaultUserInfoAdapter = DefaultUserInfoAdapter(mAttachActivity!!) {
            //startActivity<UserDetailActivity>(Pair(UserDetailActivity.EXTRA_USER_ID, it.userId))
        }
        userInfoRecyclerView.adapter = mDefaultUserInfoAdapter
    }

    fun requestUserInfoList(query: String) {
        val userList: ArrayList<UserInfoEntity> = ArrayList()
        for (item in KContactsApp.instance.userList) {
            if (item.filter(query)) {
                userList.add(item)
            }
        }
        mDefaultUserInfoAdapter?.data = userList
    }
}