package com.danke.contacts.medium.behavior

import android.content.Context
import android.util.AttributeSet
import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class ScrollingFABFadeBehavior(context: Context, attrs: AttributeSet) : AbsScrollingFABBehavior(context, attrs) {
    override fun getFABHideAnimationRes(): Int = R.anim.behavior_fade_out

    override fun getFABShowAnimationRes(): Int = R.anim.behavior_fade_in
}