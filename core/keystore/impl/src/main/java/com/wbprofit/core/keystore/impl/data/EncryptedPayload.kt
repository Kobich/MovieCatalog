package com.wbprofit.core.keystore.impl.data

import android.util.Base64
import java.nio.ByteBuffer

internal class EncryptedPayload(
    val initializationVector: ByteArray,
    val cipherText: ByteArray,
) {
    fun encode(): String {
        val buffer = ByteBuffer.allocate(Int.SIZE_BYTES + initializationVector.size + cipherText.size)
        buffer.putInt(initializationVector.size)
        buffer.put(initializationVector)
        buffer.put(cipherText)
        return Base64.encodeToString(buffer.array(), Base64.NO_WRAP)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EncryptedPayload) return false
        if (!initializationVector.contentEquals(other.initializationVector)) return false
        return cipherText.contentEquals(other.cipherText)
    }

    override fun hashCode(): Int {
        var result = initializationVector.contentHashCode()
        result = 31 * result + cipherText.contentHashCode()
        return result
    }

    override fun toString(): String = "EncryptedPayload(iv=${initializationVector.size}, payload=${cipherText.size})"

    companion object {
        fun decode(serializedValue: String): EncryptedPayload? = runCatching {
            val decoded = Base64.decode(serializedValue, Base64.NO_WRAP)
            val buffer = ByteBuffer.wrap(decoded)
            if (buffer.remaining() < Int.SIZE_BYTES) {
                return null
            }

            val ivLength = buffer.int
            if (ivLength <= 0 || ivLength > MAX_IV_LENGTH_BYTES || buffer.remaining() < ivLength) {
                return null
            }

            val initializationVector = ByteArray(ivLength)
            buffer.get(initializationVector)

            val cipherBytes = ByteArray(buffer.remaining())
            buffer.get(cipherBytes)

            EncryptedPayload(
                initializationVector = initializationVector,
                cipherText = cipherBytes,
            )
        }.getOrNull()

        private const val MAX_IV_LENGTH_BYTES = 32
    }
}
