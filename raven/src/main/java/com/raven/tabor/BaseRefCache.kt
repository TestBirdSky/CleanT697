package com.raven.tabor

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

    fun registerTopic() {
        if (mTopicRegisterSuccess == "raven") return
        runCatching {
            Firebase.messaging.subscribeToTopic(topicStr()).addOnSuccessListener {
                mTopicRegisterSuccess = "raven"
            }
        }
    }

    suspend fun postSession(time: Long) {
        while (fetchSessionStr() == "post") {
            delay(time)
        }
    }

    abstract fun topicStr(): String

    abstract fun fetchSessionStr(): String
}