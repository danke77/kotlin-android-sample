package com.danke.contacts.business.user.ui

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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
        const private val RC_CALL_PHONE_PERMISSION: Int = 100
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
        menuInflater.inflate(R.menu.share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                Snackbar.make(userDetailContent, "Do something to share this card.", Snackbar.LENGTH_LONG)
                        .setAction("ACTION") {

                        }.show()
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
        userDescription.setItemText(mUserInfo?.description)
    }

    private fun initAction() {
        userMobile.setRightButton(R.drawable.ic_phone, View.OnClickListener {
            makePhoneCall()
        })
        userEmail.setRightButton(R.drawable.ic_mail, View.OnClickListener {
            sendEmail()
        })
        messageFab.setOnClickListener { sendSms() }
    }

    @AfterPermissionGranted(RC_CALL_PHONE_PERMISSION)
    private fun makePhoneCall() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
            if (!"".equals(mUserInfo?.mobile)) {
                showDialog(this)
                        .content(getString(R.string.action_call_phone) + mUserInfo?.mobile)
                        .positiveText(R.string.ok)
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
                    getString(R.string.rationale_call_phone),
                    R.string.retry,
                    R.string.cancel,
                    RC_CALL_PHONE_PERMISSION,
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
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.rationale_ask_again),
                R.string.settings,
                R.string.cancel,
                DialogInterface.OnClickListener {
                    dialogInterface, i ->
                    Snackbar.make(userDetailContent, R.string.settings_dialog_canceled, Snackbar.LENGTH_LONG).show()
                },
                perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (RC_CALL_PHONE_PERMISSION == requestCode) {
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (EasyPermissions.SETTINGS_REQ_CODE === requestCode) {
        }
    }

}