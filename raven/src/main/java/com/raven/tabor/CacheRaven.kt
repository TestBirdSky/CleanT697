package com.raven.tabor

import android.app.Application
import android.content.Context
import java.util.UUID

/**
 * Date：2025/10/11
 * Describe:
 */
object CacheRaven {
    lateinit var mApp: Application
    var mRavenAndroidId by StringCacheImpl()
    val mSharePreference by lazy { mApp.getSharedPreferences("M_Raven", 0) }

    @JvmStatic
    fun itInt(context: Context): String {
        mApp = context as Application
        if (mRavenAndroidId.isEmpty()) {
            mRavenAndroidId = UUID.randomUUID().toString()
            return mRavenAndroidId
        }
        return ""
    }

}