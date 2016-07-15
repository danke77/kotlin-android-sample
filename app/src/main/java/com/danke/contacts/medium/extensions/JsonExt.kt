package com.danke.contacts.medium.extensions

import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONException
import java.util.*

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

fun <T> getObjectListFromJsonArray(jsonArray: JsonArray?, classOfT: Class<T>): List<T> {
    if (null == jsonArray || "null" == jsonArray.toString()) {
        return ArrayList()
    }

    val list = ArrayList<T>(jsonArray.size())
    val gson = Gson()

    try {
        for (i in 0..jsonArray.size() - 1) {
            val jsonElement = jsonArray.get(i)
            val t = gson.fromJson(jsonElement, classOfT)
            list.add(t)
        }
        return list
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ArrayList()
}

fun <T> getObjectListFromJsonArray(jsonArrayStr: String?, classOfT: Class<T>): List<T> {
    if (null == jsonArrayStr || "null" == jsonArrayStr) {
        return ArrayList()
    }

    val gson = Gson()
    try {
        val jsonArray = JSONArray(jsonArrayStr)
        val list = ArrayList<T>(jsonArray.length())
        for (i in 0..jsonArray.length() - 1) {
            list.add(gson.fromJson(jsonArray.get(i).toString(), classOfT))
        }

        return list
    } catch (e: JSONException) {
        e.printStackTrace()
    }

    return ArrayList()
}
