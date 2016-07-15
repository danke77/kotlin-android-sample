package com.danke.contacts.medium.base.fragment

import android.app.Activity
import android.app.Fragment

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */

abstract class AbsFragment : Fragment() {

    protected val TAG = javaClass.simpleName

    protected var mAttachActivity: Activity? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mAttachActivity = activity
    }

}
