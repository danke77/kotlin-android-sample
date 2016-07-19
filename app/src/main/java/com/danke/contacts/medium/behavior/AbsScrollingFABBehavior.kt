package com.danke.contacts.medium.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
abstract class AbsScrollingFABBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {

    private val SCROLL_DISTANCE = 8
    private var scrollingDown = false

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                                     child: FloatingActionButton,
                                     directTargetChild: View,
                                     target: View,
                                     nestedScrollAxes: Int): Boolean {
        // Ensure we react to vertical scrolling
        return ViewCompat.SCROLL_AXIS_VERTICAL == nestedScrollAxes
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout,
                                child: FloatingActionButton,
                                target: View,
                                dxConsumed: Int,
                                dyConsumed: Int,
                                dxUnconsumed: Int,
                                dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)

        if (dyConsumed > SCROLL_DISTANCE && !scrollingDown) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            scrollingDown = true
            child.startAnimation(AnimationUtils.loadAnimation(child.context, getFABHideAnimationRes()))
        } else if (dyConsumed < -SCROLL_DISTANCE && scrollingDown) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            scrollingDown = false
            child.startAnimation(AnimationUtils.loadAnimation(child.context, getFABShowAnimationRes()))
        }
    }

    protected abstract fun getFABHideAnimationRes(): Int

    protected abstract fun getFABShowAnimationRes(): Int

}