package com.danke.contacts.business.user.component.sortlist

import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.extensions.getFirstLetter
import java.util.*

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

class PinyinComparator : Comparator<UserInfoEntity> {

    override fun compare(o1: UserInfoEntity?, o2: UserInfoEntity?): Int = sort(o1, o2)

    private fun sort(o1: UserInfoEntity?, o2: UserInfoEntity?): Int {
        //        // 获取ascii值
        //        val sell_o1 = o1.userNamePinYinFirstLetter.toUpperCase()[0].toInt()
        //        val sell_o2 = o2.userNamePinYinFirstLetter.toUpperCase()[0].toInt()
        //        // 判断若不是字母，则排在字母之后
        //        if (sell_o1 < 65 || sell_o1 > 90)
        //            return 1;
        //        else if (sell_o2 < 65 || sell_o2 > 90)
        //            return -1;
        //        else
        //            return o1.userNamePinYin.compareTo(o2.userNamePinYin);

        if (null == o1 || null == o2) {
            return -1
        }

        val sell_o1 = getFirstLetter(o1.nicknamePinYin)
        val sell_o2 = getFirstLetter(o2.nicknamePinYin)

        if (sell_o1 == "#" && sell_o2 == "#") {
            return o1.nicknamePinYin.compareTo(o2.nicknamePinYin)
        } else if (sell_o1 == "@" && sell_o2 == "@") {
            return o1.nicknamePinYin.compareTo(o2.nicknamePinYin)
        } else if (sell_o1 == "@" || sell_o2 == "#") {
            return -1
        } else if (sell_o1 == "#" || sell_o2 == "@") {
            return 1
        } else {
            return o1.nicknamePinYin.compareTo(o2.nicknamePinYin)
        }
    }

}