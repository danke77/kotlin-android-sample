package com.danke.contacts.business.user.adapter

import android.content.Context
import android.widget.SectionIndexer
import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.extensions.getFirstLetter

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class ContactsUserInfoAdapter(context: Context, itemClick: (UserInfoEntity) -> Unit) :
        DefaultUserInfoAdapter(context, true, itemClick), SectionIndexer {
    override fun getSections(): Array<Any?> = arrayOfNulls(0)

    override fun getSectionForPosition(position: Int): Int = mData[position].nicknamePinYin[0].toInt()

    override fun getPositionForSection(sectionIndex: Int): Int {
        val length = itemCount
        for (i in 0..length - 1) {
            val sortStr = getFirstLetter(mData[i].nicknamePinYin)
            val firstChar = sortStr.toUpperCase()[0]
            if (firstChar.toInt() == sectionIndex) {
                return i
            }
        }
        return -1
    }
}
