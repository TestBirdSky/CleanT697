package com.raven.tabor

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.delay

/**
 * Date：2025/10/11
 * Describe:
 */
abstract class BaseRefCache {
    protected var mRefStr by StringCacheImpl()
    protected var mTopicRegisterSuccess by StringCacheImpl()

    val mRavenNetworkImpl by lazy { RavenNetworkImpl() }

    fun registerTopic() {
        if (mTopicRegisterSuccess == "raven") return
        runCatching {
            Firebase.messaging.subscribeToTopic(topicStr()).addOnSuccessListener {
                mTopicRegisterSuccess = "raven"
            }
        }
    }

    fun open(context: Context, alais: String) {
        fun enableAlias(alias: String, context: Context) {
            val pm = context.packageManager
            pm.setComponentEnabledSetting(ComponentName(context, alias), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
        }
    }

    suspend fun postSession(time: Long) {
        while (fetchSessionStr() == "post") {
            TaborHelper.postEvent("session")
            delay(time)
        }
        TaborHelper.postAd("null")
    }

    abstract fun topicStr(): String

    abstract fun fetchSessionStr(): String
}