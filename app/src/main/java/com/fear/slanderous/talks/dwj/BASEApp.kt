package com.fear.slanderous.talks.dwj

import android.app.Application
import com.raven.tabor.AppICenter

/**
 * Date：2025/10/16
 * Describe:
 */
abstract class BASEApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppICenter().init(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }


}