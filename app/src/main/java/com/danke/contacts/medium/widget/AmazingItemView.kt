package com.danke.contacts.medium.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.danke.contacts.R
import com.rengwuxian.materialedittext.MaterialEditText

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class AmazingItemView : FrameLayout {

    private var itemEditView: CommonEditText? = null
    private var itemTextView: TextView? = null
    private var itemSubTextView: TextView? = null
    private var itemImageButtonLeft: ImageView? = null
    private var itemImageButtonRight: ImageView? = null

    private var itemText: String? = null
    private var itemSubText: String? = null
    private var itemButtonLeft: Int = 0
    private var itemButtonRight: Int = 0

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(context, attrs)
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttrs(context, attrs)
        init(context)
    }

    private fun getAttrs(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AmazingItemView)
        itemText = ta.getString(R.styleable.AmazingItemView_itemText)
        itemSubText = ta.getString(R.styleable.AmazingItemView_itemSubText)
        itemButtonLeft = ta.getResourceId(R.styleable.AmazingItemView_itemButtonLeft, 0)
        itemButtonRight = ta.getResourceId(R.styleable.AmazingItemView_itemButtonRight, 0)
        ta.recycle()
    }

    private fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_layout, null)
        itemEditView = view.findViewById(R.id.itemEdit) as CommonEditText
        itemTextView = view.findViewById(R.id.itemText) as TextView
        itemSubTextView = view.findViewById(R.id.itemSubText) as TextView
        itemImageButtonLeft = view.findViewById(R.id.itemButtonLeft) as ImageView
        itemImageButtonRight = view.findViewById(R.id.itemButtonRight) as ImageView
        addView(view)

        itemEditView?.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NONE)

        itemTextView?.text = itemText
        itemEditView?.setText(itemText)
        itemSubTextView?.text = itemSubText
        if (0 == itemButtonLeft) {
            itemImageButtonLeft?.visibility = GONE
        } else {
            itemImageButtonLeft?.setImageResource(itemButtonLeft)
        }
        if (0 == itemButtonRight) {
            itemImageButtonRight?.visibility = GONE
        } else {
            itemImageButtonRight?.setImageResource(itemButtonRight)
        }
    }

    fun getItemEdit() = itemEditView?.text.toString()

    fun openItemEdit() {
        itemEditView?.visibility = VISIBLE
        itemTextView?.visibility = GONE
    }

    fun closeItemEdit() {
        itemEditView?.visibility = GONE
        itemTextView?.visibility = VISIBLE
    }

    fun setItemText(string: String?) {
        itemTextView?.text = string
        itemEditView?.setText(string)
    }

    fun setSubItemText(string: String?) {
        itemTextView?.text = string
    }

    fun setLeftButton(resId: Int, onClickListener: OnClickListener) {
        itemImageButtonLeft?.setImageResource(resId)
        itemImageButtonLeft?.setOnClickListener(onClickListener)
        itemImageButtonLeft?.visibility = VISIBLE
    }

    fun setRightButton(resId: Int, onClickListener: OnClickListener) {
        itemImageButtonRight?.setImageResource(resId)
        itemImageButtonRight?.setOnClickListener(onClickListener)
        itemImageButtonRight?.visibility = VISIBLE
    }

}
