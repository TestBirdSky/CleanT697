package com.amkz.kiajs.kzias

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.raven.tabor.core.FcmHelper

/**
 * Date：2025/10/16
 * Describe:
 */
class FireSer : FirebaseMessagingService() {
    private val mFcmHelper by lazy { FcmHelper() }
//
    override fun onCreate() {
        super.onCreate()
        mFcmHelper.actionService(this, "onCreate")
    }
//
    override fun onDestroy() {
        super.onDestroy()
        mFcmHelper.actionService(this, "onDestroy")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        mFcmHelper.actionService(this, "onNewToken")
    }
}