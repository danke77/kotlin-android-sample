package com.danke.contacts.business

import android.app.Application
import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.extensions.DelegatesExt
import java.util.*

/**
 * @author danke (https://github.com/danke77)
 * @date 16/3/2
 */
class KContactsApp : Application() {

    var userList: List<UserInfoEntity> = ArrayList()

    companion object {
        var instance: KContactsApp by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

