package com.wbprofit.core.secure.impl

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.wbprofit.core.secure.impl.data.EncryptedPayload
import com.wbprofit.core.secure.impl.data.EncryptionEngine
import com.wbprofit.core.utils.secure.api.SecureKeystoreStorage

internal class SecureKeystoreStorageImpl(
    context: Context,
    private val preferences: SharedPreferences = context.applicationContext.getSharedPreferences(
        DEFAULT_STORAGE_FILE,
        Context.MODE_PRIVATE,
    ),
    private val encryptionEngine: EncryptionEngine = EncryptionEngine(),
) : SecureKeystoreStorage {

    override fun save(key: String, value: String): Boolean = runCatching {
        val payload = encryptionEngine.encrypt(value.toByteArray(Charsets.UTF_8))
        preferences.edit { putString(key, payload.encode()) }
    }.onFailure { throwable ->
        Log.e(TAG, "Failed to save secure value for key: $key", throwable)
    }.isSuccess

    override fun read(key: String): String? {
        if (!preferences.contains(key)) return null
        val serializedPayload = preferences.getString(key, null) ?: return null

        val payload = EncryptedPayload.decode(serializedPayload) ?: run {
            Log.w(TAG, "Encrypted payload is corrupted for key: $key, removing entry")
            remove(key)
            return null
        }

        return runCatching {
            val decryptedBytes = encryptionEngine.decrypt(payload)
            String(decryptedBytes, Charsets.UTF_8)
        }.onFailure { throwable ->
            Log.e(TAG, "Failed to decrypt secure value for key: $key", throwable)
            remove(key)
        }.getOrNull()
    }

    override fun remove(key: String): Boolean = runCatching {
        preferences.edit { remove(key) }
    }.onFailure { throwable ->
        Log.e(TAG, "Failed to remove secure value for key: $key", throwable)
    }.isSuccess

    override fun clear() {
        runCatching { preferences.edit { clear() } }.onFailure { throwable ->
            Log.e(TAG, "Failed to clear secure storage", throwable)
        }
    }

    override fun contains(key: String): Boolean = preferences.contains(key)

    companion object {
        private const val TAG = "SecureKeystoreStorage"
        private const val DEFAULT_STORAGE_FILE = "wb_secure_storage"
    }
}
