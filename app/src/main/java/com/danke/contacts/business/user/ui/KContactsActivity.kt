package com.danke.contacts.business.user.ui

import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatDelegate
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
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_contacts.*
import java.io.ByteArrayOutputStream
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> menu.findItem(R.id.menu_night_mode_system).isChecked = true
            AppCompatDelegate.MODE_NIGHT_NO -> menu.findItem(R.id.menu_night_mode_day).isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> menu.findItem(R.id.menu_night_mode_night).isChecked = true
            AppCompatDelegate.MODE_NIGHT_AUTO -> menu.findItem(R.id.menu_night_mode_auto).isChecked = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> startActivity<UserSearchActivity>()
            R.id.menu_night_mode_system -> setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            R.id.menu_night_mode_day -> setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            R.id.menu_night_mode_night -> setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            R.id.menu_night_mode_auto -> setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setNightMode(@AppCompatDelegate.NightMode nightMode: Int) {
        AppCompatDelegate.setDefaultNightMode(nightMode)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            recreate()
        }
    }

    override fun isStatusBarTransparent() = true

    override fun toggleOverridePendingTransition() = false

    override fun getOverridePendingTransitionMode() = TransitionMode.NONE

    private fun initToolbar() {
        title = getString(R.string.app_name)
    }

    private fun initFab() {
        mainFab.setOnClickListener({
            Snackbar.make(it, "Do something to add one card.", Snackbar.LENGTH_LONG)
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
                // the position this letter first appears
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
        loadStart()

        HandlerUtil().handle<UserInfoEntity>(object : HandlerUtil.HandlerCallBack {
            override fun <T> runWorkThread(): List<T> {
                val inputStream = this@KContactsActivity.resources.openRawResource(R.raw.contacts)
                val byteArrayOutputStream = ByteArrayOutputStream()

                var i: Int
                i = inputStream.read()
                while (i != -1) {
                    byteArrayOutputStream.write(i)
                    i = inputStream.read()
                }

                val jsonArray: JsonArray = JsonParser().parse(byteArrayOutputStream.toString()).asJsonArray
                val userList = getObjectListFromJsonArray(jsonArray, UserInfoEntity::class.java)
                for (item in userList) {
                    item.userNamePinYin = mPinyinParser?.getSelling(item.username)?.toLowerCase() as String
                    item.nicknamePinYin = mPinyinParser?.getSelling(item.nickname)?.toLowerCase() as String
                }
                Collections.sort(userList, mPinyinComparator)

                return userList as List<T>
            }

            override fun mainThreadCallback(obj: Any) {
                KContactsApp.instance.userList = obj as List<UserInfoEntity>
                mContactsUserInfoAdapter?.data = KContactsApp.instance.userList
                loadEnd()
            }
        })
    }

}
