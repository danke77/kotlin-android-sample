package com.danke.contacts.business.user.ui

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.danke.contacts.R
import com.danke.contacts.business.user.constant.Sex
import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.base.activity.AbsSwipeBackActivity
import com.danke.contacts.medium.extensions.*
import kotlinx.android.synthetic.main.activity_user_detail.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

class UserDetailActivity : AbsSwipeBackActivity(), EasyPermissions.PermissionCallbacks {

    private var mUserInfo: UserInfoEntity? = null

    companion object {
        val EXTRA_USER_INFO = "UserDetailActivity:USER_INFO"
        const private val PERMISSION_RC_CALL_PHONE: Int = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        mUserInfo = intent.getParcelableExtra(EXTRA_USER_INFO)

        initCollapsingToolbarLayout()
        loadBackdrop()
        initUserInfo()
        initAction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.done, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit -> {
                //share
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun isStatusBarTransparent() = true


    private fun initCollapsingToolbarLayout() {
        userDetailCollapsingToolbar.title = mUserInfo?.nickname
    }

    private fun loadBackdrop() {
        userDetailBackdrop.loadFromUrl(this, mUserInfo?.avatar!!, NO_PLACE_HOLDER)
    }

    private fun initUserInfo() {
        userMobile.setItemText(mUserInfo?.mobile)
        userEmail.setItemText(mUserInfo?.email)
        userBirthday.setItemText(mUserInfo?.birthday)
        var sex = ""
        when (mUserInfo?.sex) {
            Sex.MALE -> sex = getString(R.string.male)
            Sex.FEMALE -> sex = getString(R.string.female)
            Sex.UNKNOWN -> sex = ""
        }
        userSex.setItemText(sex)
    }

    private fun initAction() {
        userMobile.setLeftButton(R.drawable.icon_sms, View.OnClickListener {
            sendSms()
        })
        userMobile.setRightButton(R.drawable.icon_phone, View.OnClickListener {
            makePhoneCall()
        })
        userEmail.setRightButton(R.drawable.icon_mail, View.OnClickListener {
            sendEmail()
        })
    }

    @AfterPermissionGranted(PERMISSION_RC_CALL_PHONE)
    private fun makePhoneCall() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
            if (!"".equals(mUserInfo?.mobile)) {
                showDialog(this)
                        .title(R.string.tip)
                        .content(getString(R.string.call_phone) + mUserInfo?.mobile)
                        .positiveText(R.string.confirm)
                        .negativeText(R.string.cancel)
                        .onPositive {
                            materialDialog, dialogAction ->
                            actionMakePhoneCall(this, mUserInfo?.mobile!!)
                        }
                        .cancelable(true)
                        .show()
            }
        } else {
            EasyPermissions.requestPermissions(this,
                    getString(R.string.permission_rationale_call_phone),
                    R.string.confirm,
                    R.string.cancel,
                    PERMISSION_RC_CALL_PHONE,
                    Manifest.permission.CALL_PHONE)
        }
    }

    private fun sendSms() {
        actionSendSms(this, mUserInfo?.mobile!!)
    }

    private fun sendEmail() {
        actionSendEmail(this, mUserInfo?.email!!)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        showToast(this, R.string.permission_cancel)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}