package com.danke.contacts.business.user.entity

import android.os.Parcel
import android.os.Parcelable
import com.danke.contacts.business.user.constant.Sex
import com.danke.contacts.medium.extensions.createParcel
import com.google.gson.annotations.SerializedName

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

data class UserInfoEntity(
        @SerializedName("id") var userId: Long = 0,
        @SerializedName("username") var username: String = "",
        @SerializedName("nickname") var nickname: String = "",
        @SerializedName("avatar") var avatar: String = "",
        @SerializedName("birthday") var birthday: String = "",
        @SerializedName("description") var description: String = "",
        @SerializedName("sex") var sex: Int = Sex.UNKNOWN,
        @SerializedName("email") var email: String = "",
        @SerializedName("mobile") var mobile: String = "",
        @Transient var userNamePinYin: String = "",
        @Transient var nicknamePinYin: String = ""
) : Parcelable {
    fun filter(query: String): Boolean =
            !"".equals(query)
                    && (userNamePinYin.contains(query)
                    || nicknamePinYin.contains(query)
                    || nickname.contains(query)
                    || username.contains(query)
                    || mobile.contains(query)
                    || email.contains(query))

    constructor(parcelIn: Parcel) : this() {
        this.userId = parcelIn.readLong()
        this.username = parcelIn.readString()
        this.nickname = parcelIn.readString()
        this.avatar = parcelIn.readString()
        this.birthday = parcelIn.readString()
        this.description = parcelIn.readString()
        this.sex = parcelIn.readInt()
        this.email = parcelIn.readString()
        this.mobile = parcelIn.readString()
        this.userNamePinYin = parcelIn.readString()
        this.nicknamePinYin = parcelIn.readString()
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(userId)
        dest?.writeString(username)
        dest?.writeString(nickname)
        dest?.writeString(avatar)
        dest?.writeString(birthday)
        dest?.writeString(description)
        dest?.writeInt(sex)
        dest?.writeString(email)
        dest?.writeString(mobile)
        dest?.writeString(userNamePinYin)
        dest?.writeString(nicknamePinYin)
    }

    companion object {
        @JvmField
        val CREATOR = createParcel { UserInfoEntity(it) }
    }
}