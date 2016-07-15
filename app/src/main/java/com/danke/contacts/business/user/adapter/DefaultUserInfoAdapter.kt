package com.danke.contacts.business.user.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.danke.contacts.R
import com.danke.contacts.business.user.entity.UserInfoEntity
import com.danke.contacts.medium.extensions.ctx
import com.danke.contacts.medium.extensions.getFirstLetter
import com.youzan.titan.TitanAdapter
import kotlinx.android.synthetic.main.user_list_item.view.*

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
open class DefaultUserInfoAdapter(val context: Context, val hasCatalog: Boolean = false, val itemClick: (UserInfoEntity) -> Unit) :
        TitanAdapter<UserInfoEntity>() {

    private val mTypedValue = TypedValue()
    private var mBackground = 0

    init {
        context.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true)
        mBackground = mTypedValue.resourceId
    }

    override fun createVHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getAdapterItemId(position: Int): Long = position.toLong()

    override fun showItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            holder.bindUserInfo(this, position, mData)
        }
    }

    class ViewHolder(view: View, val itemClick: (UserInfoEntity) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindUserInfo(defaultUserInfoAdapter: DefaultUserInfoAdapter, position: Int, mData: List<UserInfoEntity>) {
            with(mData[position]) {
                itemView.userInfoLayout.setBackgroundResource(defaultUserInfoAdapter.mBackground)
                itemView.userAvatar.setAvatar(avatar, nickname)
                itemView.userName.text = nickname
                itemView.setOnClickListener({ itemClick(this) })

                if (!defaultUserInfoAdapter.hasCatalog) {
                    val params = itemView.userAvatar.layoutParams as RelativeLayout.LayoutParams
                    params.leftMargin = 0
                    itemView.userAvatar.layoutParams = params
                }

                val currentStr = getFirstLetter(nicknamePinYin)
                val previewStr = if (position - 1 >= 0) {
                    getFirstLetter(mData[position - 1].nicknamePinYin)
                } else {
                    " "
                }

                if (!previewStr.equals(currentStr) && defaultUserInfoAdapter.hasCatalog) {
                    itemView.itemCatalogLayout.visibility = View.VISIBLE
                    itemView.itemCatalogText.text = currentStr
                } else {
                    itemView.itemCatalogLayout.visibility = View.GONE
                }
            }
        }
    }
}
