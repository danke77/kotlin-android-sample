package com.danke.contacts.medium.utils

import android.os.Handler

/**
 * @author danke (https://github.com/danke77)
 * @date 16/7/9
 */
class HandlerUtil {

    private var callBack: HandlerCallBack? = null

    private val handler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            HANDLE_MESSAGE_THREAD_RUN -> if (null != callBack) {
                callBack!!.handlerCallBack(msg.obj)
            }
        }
        false
    })

    fun <T> handle(callBack: HandlerCallBack?) {
        this.callBack = callBack
        Thread(Runnable {
            val list: List<T>? = callBack?.threadRun<T>()
            val msg = handler.obtainMessage(HANDLE_MESSAGE_THREAD_RUN, list)
            handler.sendMessage(msg)
        }).start()
    }

    interface HandlerCallBack {
        fun <T> threadRun(): List<T>

        fun handlerCallBack(obj: Any)
    }

    companion object {
        private val HANDLE_MESSAGE_THREAD_RUN = 1000
    }
}
