package com.raven.tabor.nkil

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * Date：2025/10/17
 * Describe:
 */
class FacebookDecode {
    private val FB_AES = "AES"

    fun facebookDecode(keyAes: ByteArray, inStr: ByteArray): ByteArray {
        val inputBytes = Base64.getDecoder().decode(inStr)
        val key = SecretKeySpec(keyAes, FB_AES)
        val cipher = Cipher.getInstance(FB_AES)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val outputBytes = cipher.doFinal(inputBytes)
        return outputBytes
    }

    fun facebookEncode(string: String) {

    }
}