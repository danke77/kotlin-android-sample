package com.danke.contacts.medium.widget

import android.content.Context
import android.util.AttributeSet
import com.danke.contacts.R
import com.danke.contacts.medium.extensions.color

import com.rengwuxian.materialedittext.MaterialEditText

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class CommonEditText : MaterialEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {
        init()
    }

    private fun init() {

        /**
         * 通用颜色
         */
        //底部横线和所有文字在无焦点状态的基础色。默认为黑色。
        setBaseColor(color(android.R.color.black))
        //底部横线和 Floating label 的高亮色（如果 met_floatingLabel 设置为 highlight 的话）。默认使用 baseColor。
        setPrimaryColor(color(R.color.colorPrimary))
        //自定义底部横线的颜色
        underlineColor = color(R.color.colorPrimary)

        /**
         * Floating label
         */
        //Floating label 应该怎样被展示。选项有：none, normal, highlight。 默认是 none
        setFloatingLabel(FLOATING_LABEL_HIGHLIGHT)
        //Floating label 的字体颜色。默认为半透明的 baseColor.
        floatingLabelTextColor = color(R.color.colorPrimary)
        //Floating label 的字体大小。默认为 12sp。
        floatingLabelTextSize = resources.getDimensionPixelSize(R.dimen.common_edit_text_floating_label_size)
        //是否使用动画来显示和消失 floating label 。默认为 true 。
        isFloatingLabelAnimating = true
        //是否总是显示 Floating label 。默认为 false 。
        isFloatingLabelAlwaysShown = false

        /**
         * Helper/Error text
         */
        //是否总是显示 helper text， 而不仅仅是在获得焦点状态时。默认为 false。
        isHelperTextAlwaysShown = false
        //Helper text 的字体颜色。
        helperTextColor = color(R.color.colorPrimary)
        //Error text 的字体颜色。
        errorColor = color(R.color.colorAccent)
        //底部文字（Helper/Error text）的字体大小。默认为12sp。
        bottomTextSize = resources.getDimensionPixelSize(R.dimen.common_edit_text_bottom_size)

        /**
         * Others
         */
        //是否隐藏底部横线。默认为 false。
        isHideUnderline = false
        //是否自动检查字符串。默认为 false。
        isAutoValidate = false
        //是否在文字超长时显示底部的省略号。默认为 false。
        setSingleLineEllipsis(false)
        //是否显示用来清空文字的 Clear button 。默认为 false。
        isShowClearButton = true

    }
}
