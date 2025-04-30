package com.example.it.utils

import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object EncryptUtils {
    private const val ALGORITHM = "DESede" // 3DES算法
    private const val TRANSFORMATION = "DESede/ECB/PKCS5Padding" // 3DES算法模式和填充方式

    // 3DES加密
    @Throws(Exception::class)
    fun encryptTripleDES(data: ByteArray, key: ByteArray): ByteArray {
        val secretKey: SecretKey = SecretKeySpec(key, ALGORITHM)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher.doFinal(data)
    }

    // 3DES解密
    @Throws(Exception::class)
    fun decryptTripleDES(encryptedData: ByteArray, key: ByteArray): ByteArray {
        val secretKey: SecretKey = SecretKeySpec(key, ALGORITHM)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        return cipher.doFinal(encryptedData)
    }

    // 示例用法
    @JvmStatic
    fun main(args: Array<String>?) {
        try {
            val data = "Hello, World!"
            val key = "0123456789abcdef01234567".toByteArray() // 24字节的密钥

            // 加密
            val encryptedData = encryptTripleDES(data.toByteArray(), key)
            println("加密后的数据: " + Base64.getEncoder().encodeToString(encryptedData))

            // 解密
            val decryptedData = decryptTripleDES(encryptedData, key)
            println("解密后的数据: " + String(decryptedData))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}