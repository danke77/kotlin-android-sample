package com.danke.contacts.medium.extensions

import java.util.regex.Pattern

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

/**
 * get the first letter of one string
 */
fun getFirstLetter(str: String?): String {
    if (str == null) {
        return "#"
    }
    if (str.trim().length == 0) {
        return "#"
    }
    val c = str.trim().substring(0, 1)[0]
    // 正则表达式匹配
    val pattern = Pattern.compile("^[A-Za-z]+$")
    if (pattern.matcher(c + "").matches()) {
        return (c + "").toUpperCase() // 将小写字母转换为大写
    } else {
        return "#"
    }
}