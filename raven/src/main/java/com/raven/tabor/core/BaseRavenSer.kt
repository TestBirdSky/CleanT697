package com.raven.tabor.core

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Date：2025/10/11
 * Describe:
 */
abstract class BaseRavenSer : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        stopForeground(Service.STOP_FOREGROUND_REMOVE)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }
}