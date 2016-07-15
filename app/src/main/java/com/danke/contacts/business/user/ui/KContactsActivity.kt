package com.danke.contacts.business.user.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.danke.contacts.R
import com.danke.contacts.business.KContactsApp
import com.danke.contacts.business.user.adapter.ContactsUserInfoAdapter
import com.danke.contacts.business.user.component.sortlist.CharacterParser
import com.danke.contacts.business.user.component.sortlist.OnTouchingLetterChangedListener
import com.danke.contacts.business.user.component.sortlist.PinyinComparator
import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.base.activity.AbsToolbarActivity
import com.danke.contacts.medium.component.recycler.WrapContentLinearLayoutManager
import com.danke.contacts.medium.extensions.getObjectListFromJsonArray
import com.danke.contacts.medium.extensions.startActivity
import com.danke.contacts.medium.utils.HandlerUtil
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.*

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

class KContactsActivity : AbsToolbarActivity() {

    private var mFirstLoad = true
    private var mLoadSuccess = false

    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mContactsUserInfoAdapter: ContactsUserInfoAdapter? = null

    private var mPinyinComparator: PinyinComparator? = null
    private var mPinyinParser: CharacterParser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        mPinyinComparator = PinyinComparator()
        mPinyinParser = CharacterParser.getInstance()

        initToolbar()
        initFab()
        initSwipeRefreshLayout()
        initRecyclerView()
        initSideBar()

        updateUserInfoList()

    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                startActivity<UserSearchActivity>()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun isStatusBarTransparent() = true

    override fun toggleOverridePendingTransition() = false

    override fun getOverridePendingTransitionMode() = TransitionMode.NONE

    private fun initToolbar() {
        title = getString(R.string.app_name)
    }

    private fun initFab() {
        mainFab.setOnClickListener({
            Snackbar.make(it, "This is a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("ACTION") {

                    }.show()
        })
    }

    private fun initSwipeRefreshLayout() {
        contactsContainer.setColorSchemeResources(R.color.refresh_color_scheme_0, R.color.refresh_color_scheme_1)
        contactsContainer.setOnRefreshListener { updateUserInfoList() }
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = WrapContentLinearLayoutManager(this)
        contactsRecyclerView.layoutManager = mLinearLayoutManager

        mContactsUserInfoAdapter = ContactsUserInfoAdapter(this) {
            startActivity<UserDetailActivity>(Pair(UserDetailActivity.EXTRA_USER_INFO, it))
        }
        contactsRecyclerView.adapter = mContactsUserInfoAdapter
    }

    private fun initSideBar() {
        contactsSideBar.setTextView(contactsFloatLetter)
        contactsSideBar.setOnTouchingLetterChangedListener(object : OnTouchingLetterChangedListener {
            override fun onTouchingLetterChanged(s: String) {
                //该字母首次出现的位置
                val position = mContactsUserInfoAdapter?.getPositionForSection(s[0].toInt()) as Int
                if (position != -1) {
                    mLinearLayoutManager?.scrollToPositionWithOffset(position, 0)
                }
            }
        })
    }

    private fun loadStart() {
        mLoadSuccess = false
        if (mFirstLoad) {
            contactsContainer.isEnabled = false
            showProgressBar()
        } else {
            contactsContainer.isEnabled = true
        }
    }

    private fun loadEnd() {
        if (mFirstLoad) {
            mFirstLoad = false
            contactsContainer.isEnabled = true
            dismissProgressBar()
        } else {
            contactsContainer.isRefreshing = false
        }
    }

    private fun updateUserInfoList() {
        val jsonString: String = "{\"id\":1,\"username\":\"asdgqwrg\",\"nickname\":\"asdgqwrg\",\"avatar\":\"\",\"birthday\":\"2016-01-22\",\"sex\":0,\"email\":\"email\",\"mobile\":\"12314141241\" }"
        val parser: JsonParser = JsonParser()
        val jsonObject: JsonObject = parser.parse(jsonString).asJsonObject
        val jsonArray: JsonArray = JsonArray()
        for (i in 0..100) {
            jsonArray.add(jsonObject)
        }

        loadStart()

        HandlerUtil().handle<UserInfoEntity>(object : HandlerUtil.HandlerCallBack {
            override fun <T> threadRun(): List<T> {
                val userList = getObjectListFromJsonArray(jsonArray, UserInfoEntity::class.java)
                for (item in userList) {
                    item.userNamePinYin = mPinyinParser?.getSelling(item.username)?.toLowerCase() as String
                    item.nicknamePinYin = mPinyinParser?.getSelling(item.nickname)?.toLowerCase() as String
                }
                Collections.sort(userList, mPinyinComparator)
                return userList as List<T>
            }

            override fun handlerCallBack(obj: Any) {
                KContactsApp.instance.userList = obj as List<UserInfoEntity>
                mContactsUserInfoAdapter?.data = KContactsApp.instance.userList
                loadEnd()
            }
        })
    }

}
